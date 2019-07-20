package com.gl.ceir.config.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.FileStorageException;
import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.Tac;
import com.gl.ceir.config.model.UploadFileResponse;
import com.gl.ceir.config.repository.TacRepository;
import com.gl.ceir.config.service.TacService;

@Service
public class TacServiceImpl implements TacService {
	private final Logger logger = LogManager.getLogger(ScriptServiceImpl.class);

	@Autowired
	private TacRepository tacRepository;

	@Autowired
	private TacFileLoaderInDB tacFileLoader;

	private Path fileStorageProperties;

	@Autowired
	public TacServiceImpl(FileStorageProperties fileStorageProperties) {
		System.out.println("fileStorageProperties " + fileStorageProperties.getUploadDir());
		this.fileStorageProperties = Paths.get(fileStorageProperties.getTacUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageProperties);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	@Override
	public List<Tac> getAll() {
		try {
			return tacRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	@Caching(put = { @CachePut(value = "tac", key = "#tac.id") })
	public Tac save(Tac tac) {
		try {
			return tacRepository.save(tac);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public Tac get(Long id) {
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Tac update(Tac tac) {
		try {
			return tacRepository.save(tac);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	@Cacheable(value = "tac", key = "#id")
	public Tac get(String id) {
		try {
			Tac tac = tacRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tac ", "id", id));
			return tac;
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public UploadFileResponse upload(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// String fileType = file.getContentType().split("/")[1];
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageProperties.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			logger.info("Tac file Saved Successfully");

			tacFileLoader.setPath(targetLocation);
			
			new Thread(tacFileLoader).start();
			
			return new UploadFileResponse(fileName, null, file.getContentType(), file.getSize());

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

}
