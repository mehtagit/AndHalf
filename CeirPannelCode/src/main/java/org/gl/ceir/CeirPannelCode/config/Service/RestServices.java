package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.List;

public interface RestServices<T> {
	public List<T> getAll();

	public T save(T t);

	public T get(Long id);

	public void delete(Long t);

	public T update(T t);
}
