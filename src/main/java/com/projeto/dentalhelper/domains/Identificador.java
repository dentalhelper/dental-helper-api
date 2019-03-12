package com.projeto.dentalhelper.domains;

import java.io.Serializable;

public interface Identificador<T> extends Serializable {
	T getCodigo();

	void setCodigo(T codigo);

	default boolean isSalvo() {
		return this.getCodigo() != null;
	}
}
