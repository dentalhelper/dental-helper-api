package com.projeto.dentalhelper.domains.enums;

public enum FaceDente {
	O(1, "O"),
	L(2, "L"),
	M(3, "M"),
	V(4, "V"),
	D(5, "D"),
	I(6, "I"),
	P(7, "P"),
	MO(8, "MO"),
	DO(9, "DO"),
	MV(10, "MV"),
	DV(11, "DV"),
	OV(12, "OV"),
	ML(13, "ML"),
	MP(14, "MP"),
	DL(15, "DL"),
	DP(16, "DP"),
	OL(17, "OL"),
	OP(18, "OP"),
	MI(19, "MI"),
	DI(20, "DI"),
	MOD(21, "MOD"),
	MVD(22, "MVD"),
	MOV(23, "MOV"),
	DOV(24, "DOV"),
	MLD(25, "MLD"),
	MPD(26, "MPD"),
	MOL(27, "MOL"),
	MOP(28, "MOP"),
	DOL(29, "DOL"),
	DOP(30, "DOP"),
	VML(31, "VML"),
	VMP(32, "WMP"),
	VDL(33, "VDL"),
	VDP(34, "VDP"),
	VOL(35, "VOL"),
	VOP(36, "VOP"),
	MID(37, "MID"),
	MODV(38, "MODV"),
	MODL(39, "MODL"),
	MODP(40, "MODP"),
	VMDL(41, "VMDL"),
	VMDP(42, "VMDP"),
	MOVL(43, "MOVL"),
	MOVP(44, "MOVP"),
	DOVL(45, "DOVL"),
	DOVP(46, "DOVP"),
	MODVL(47, "MODVL"),
	MODVP(48, "MODVP");
	
	private int codigo;
	private String descricao;
	private FaceDente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static FaceDente toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (FaceDente status : FaceDente.values()) {
			if (codigo.equals(status.getCodigo())) {
				return status;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}
