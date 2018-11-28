package com.github.saulocalixto.Invscp.servidor.enumeradores;


public enum EnumGrupoDeMaterial {
    MOBILIA_DECORACAO ("Mobilia e Decoração", 10, 10),
    AUTOMOVEL ("Automóvel", 15, 10),
    OBRA_ARTE ("Obra de Arte", 10, 0),
    COMPUTADOR ("Computador", 5, 10),
    MATERIAL_BIBLIOGRAFICO ("Material Bibliográfico", 10, 0),
    UTENSILIO_HOSPITALAR ("Equipamento e Utensilio Hospitalar", 15, 20),
    APARELHO_USO_QUIMICO ("Aparelho de Uso Químico", 5, 20),
    MATERIAL_TEXTIL ("Material Têxtil", 5, 20),
    FERRAMENTA ("Feramenta", 5, 20),
    REATOR_NUCLEAR("Reator Nuclear", 10, 10),
    MAQUINA_RESFRIAMENTO_AMBIENTE ("Máquina de Resfriamento de Ambiente", 10, 10),
    CAMERA_PROJETOR ("Camera e Projetor", 10, 10),
    REFRIGERADOR_FREEZER ("Regrigerador e Freezer", 10, 10);




    private String descricao;

    private int vidaUtil;

    private int taxaDepreciacaoAnual;

    private EnumGrupoDeMaterial (String descricao, int vidaUtil, int taxaDepreciacaoAnual) {
        this.descricao = descricao;
        this.vidaUtil = vidaUtil;
        this.taxaDepreciacaoAnual = taxaDepreciacaoAnual;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public int getTaxaDepreciacaoAnual() {
        return taxaDepreciacaoAnual;
    }
}
