package com.projeto.dentalhelper.repositories.ImagemAnexada;

import java.util.List;

import com.projeto.dentalhelper.domains.ImagemAnexada;

public interface ImagemAnexadaRepositoryQuery {
	public List<ImagemAnexada> buscarPorCodigoDeProcedimentoPrevisto(Long codigo);

}
