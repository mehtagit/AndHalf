package read.excel.ReadExcel.FileReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import read.excel.ReadExcel.configuration.PropertiesFileReader;
import read.excel.ReadExcel.dao.DistrictDao;
import read.excel.ReadExcel.entity.Commune;
import read.excel.ReadExcel.entity.District;
import read.excel.ReadExcel.entity.Locality;
import read.excel.ReadExcel.entity.Province;
import read.excel.ReadExcel.entity.Village;
import read.excel.ReadExcel.repo.CommuneRepo;
import read.excel.ReadExcel.repo.DistrictRepo;
import read.excel.ReadExcel.repo.LocalityRepo;
import read.excel.ReadExcel.repo.ProvinceRepo;
import read.excel.ReadExcel.repo.VillageRepo;

@Component
public class FileReader {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	DistrictDao districtDao;
	@Autowired
	DistrictRepo districtRepo;
	@Autowired
	CommuneRepo communeRepo;
	@Autowired
	VillageRepo villageRepo;
	@Autowired
	PropertiesFileReader propertiesFileReader;
	@Autowired
	LocalityRepo localityRepo;
	@Autowired
	ProvinceRepo provinceRepo;

	public void fileRead() {
		try {
			String path = propertiesFileReader.sourcePath;

			File myFile = new File(path);
			FileInputStream fis = new FileInputStream(myFile);

			// Finds the workbook instance for XLSX file
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

			// Reading sheet at number 0 in spreadsheet(image attached for reference
			Sheet sheet = myWorkBook.getSheetAt(0);
			log.info("started reading excel file");

			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				Cell provinceCell = row.getCell(1);
				Cell districtCell = row.getCell(2);
				// Printing Stuff

				String province = provinceCell.getStringCellValue();
				String district = districtCell.getStringCellValue();

				Boolean isExistInDB = districtDao.existsByProvinceAndDistrict(province, district);
				if (isExistInDB) {
					log.info("No value insert for " + province + " and " + district + " because it exist in DB");
					continue;
				} else {
					District request = new District();
					request.setProvince(province);
					request.setDistrict(district);
					districtRepo.save(request);
					log.info("request : " + request);
					log.info("Successful insertion in DB for " + province + " and " + district);
				}
			}

			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commune() {

		try {
			String path = propertiesFileReader.sourcePath;

			File myFile = new File(path);
			FileInputStream fis = new FileInputStream(myFile);

			// Finds the workbook instance for XLSX file
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

			// Reading sheet at number 0 in spreadsheet(image attached for reference
			Sheet sheet = myWorkBook.getSheetAt(0);
			log.info("started reading excel file");

			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				Cell provinceCell = row.getCell(1);
				Cell districtCell = row.getCell(2);
				Cell communeCell = row.getCell(3);
				// Printing Stuff
				String commune = communeCell.getStringCellValue();
				String district = districtCell.getStringCellValue();
				District object = districtRepo.findByDistrictAndProvince(district, provinceCell.getStringCellValue());

				Optional<Commune> communeObject = Optional
						.ofNullable(communeRepo.findByDistrictIDAndCommune(Long.valueOf(object.getId()), commune));
				Boolean isExist = communeObject.isPresent();
				if (isExist) {
					log.info("already exist value in DB for " + commune + " and " + object.getDistrict());
				} else {
					Commune request = new Commune();
					request.setDistrictID(object.getId());
					request.setCommune(commune);
					communeRepo.save(request);
					log.info("request : " + request);
					log.info("Successful insertion in DB for " + commune + " and " + district);
				}
			}

			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void village() {

		try {
			String path = propertiesFileReader.sourcePath;

			File myFile = new File(path);
			FileInputStream fis = new FileInputStream(myFile);

			// Finds the workbook instance for XLSX file
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

			// Reading sheet at number 0 in spreadsheet(image attached for reference
			Sheet sheet = myWorkBook.getSheetAt(0);
			log.info("started reading excel file");

			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);

				Cell districtCell = row.getCell(2);
				Cell communeCell = row.getCell(3);
				Cell villageCell = row.getCell(4);
				// Printing Stuff
				String commune = communeCell.getStringCellValue();
				String district = districtCell.getStringCellValue();
				District districtObject = districtRepo.findByDistrict(district);
				log.info(district + "districtObject : " + districtObject);
				Commune object = communeRepo.findByDistrictIDAndCommune(Long.valueOf(districtObject.getId()), commune);

				log.info("commune object : " + object);

				if (villageCell.getStringCellValue().isEmpty()) {
					Village request = new Village();
					request.setComuneID(object.getId());
					request.setVillage("N/A");
					villageRepo.save(request);
					log.info("request : " + request);
					log.info("Successful insertion in DB for " + commune + " with N/A village");
				} else {
					String[] values = villageCell.getStringCellValue().split(",");

					for (int j = 0; j < values.length; j++) {

						if (values[j].isEmpty()) {
							log.info("this time No more value exist for " + commune);
						} else {
							Village request = new Village();
							request.setComuneID(object.getId());
							request.setVillage(values[j]);
							villageRepo.save(request);
							log.info("request : " + request);
							log.info("Successful insertion in DB for " + commune + " and " + values[j]);

						}
					}
				}

			}

			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void upload() {
		try {
			String path = propertiesFileReader.sourcePath;

			File myFile = new File(path);
			FileInputStream fis = new FileInputStream(myFile);

			// Finds the workbook instance for XLSX file
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

			// Reading sheet at number 0 in spreadsheet(image attached for reference
			Sheet sheet = myWorkBook.getSheetAt(0);
			log.info("started reading excel file");

			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				Cell countryCell = row.getCell(0);
				Cell provinceCell = row.getCell(1);
				Cell districtCell = row.getCell(2);
				Cell communeCell = row.getCell(3);
				Cell villageCell = row.getCell(4);
				// Printing Stuff
				String country = countryCell.getStringCellValue();
				String province = provinceCell.getStringCellValue();
				String district = districtCell.getStringCellValue().isEmpty() ? "N/A"
						: districtCell.getStringCellValue();
				String commune = communeCell.getStringCellValue().isEmpty() ? "N/A" : communeCell.getStringCellValue();
				/*
				 * String village = villageCell.getStringCellValue().isEmpty()
				 * ?"N/A":villageCell.getStringCellValue() ;
				 */

				Locality request = new Locality();
				String villageVal;
				String[] values = villageCell.getStringCellValue().split(",");

				for (int j = 0; j < values.length; j++) {

					villageVal = values[j].isEmpty() ? "N/A" : values[j];

					Boolean isExistInDB = localityRepo.existsByCountryAndProvinceAndDistrictAndCommuneAndVillage(
							country, province, district, commune, villageVal);
					if (isExistInDB) {
						log.info("No value insert for " + district + " and " + villageVal + " because it exist in DB");
						continue;
					} else {
						request.setCountry(country);
						request.setProvince(province);
						request.setDistrict(district);
						request.setCommune(commune);
						request.setVillage(villageVal);
						localityRepo.save(request);
						log.info("request : " + request);
						log.info("Successful insertion in DB for " + district + " and " + villageVal);
					}
				}
			}

			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void province() {
		try {
			String path = propertiesFileReader.sourcePath;

			File myFile = new File(path);
			FileInputStream fis = new FileInputStream(myFile);

			// Finds the workbook instance for XLSX file
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

			// Reading sheet at number 0 in spreadsheet(image attached for reference
			Sheet sheet = myWorkBook.getSheetAt(0);
			log.info("started reading excel file");

			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				Cell countryCell = row.getCell(0);
				Cell provinceCell = row.getCell(1);

				// Printing Stuff

				String province = provinceCell.getStringCellValue();
				String country = countryCell.getStringCellValue();

				Boolean isExistInDB = provinceRepo.existsByCountryAndProvince(country, province);
				if (isExistInDB) {
					log.info("No value insert for " + province + " because it exist in DB");
					continue;
				} else {
					Province request = new Province();
					request.setCountry(country);
					request.setProvince(province);
					provinceRepo.save(request);
					log.info("request : " + request);
				log.info("Successful insertion in DB for " + province );
				}
			}

			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
