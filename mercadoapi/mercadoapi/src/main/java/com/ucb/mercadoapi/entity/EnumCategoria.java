package com.ucb.mercadoapi.entity;

import lombok.Getter;

@Getter
public enum EnumCategoria {
    ALIMENTOS(1, "Alimentos", "Grãos, massas, enlatados, óleos, temperos e produtos de mercearia em geral."),
    BEBIDAS(2, "Bebidas", "Águas, refrigerantes, sucos, chás, cafés e bebidas alcoólicas."),
    LATICINIOS_E_FRIOS(3, "Laticínios e Frios", "Leites, queijos, iogurtes, manteigas, presuntos e embutidos."),
    ACOUGUE_E_PEIXARIA(4, "Açougue e Peixaria", "Carnes bovinas, suínas, aves, peixes e frutos do mar frescos."),
    HORTIFRUTI(5, "Hortifruti", "Frutas, legumes, verduras e temperos frescos selecionados."),
    PADARIA_E_CONFEITARIA(6, "Padaria e Confeitaria", "Pães frescos, bolos, tortas, salgados e biscoitos."),
    HIGIENE_PESSOAL(7, "Higiene Pessoal", "Sabonetes, shampoos, cremes dentais, desodorantes e cuidados pessoais."),
    LIMPEZA(8, "Limpeza", "Detergentes, sabão em pó, desinfetantes, água sanitária e utensílios de limpeza."),
    PET_SHOP(9, "Pet Shop", "Rações, petiscos, areia sanitária e acessórios para animais de estimação."),
    UTILIDADES_DOMESTICAS(10, "Utilidades Domésticas", "Itens de cozinha, descartáveis, lâmpadas, pilhas e organização."),
    BEBES(11, "Bebês", "Fraldas, lenços umedecidos, fórmulas infantis e cuidados para bebês."),
    CONGELADOS(12, "Congelados", "Pratos prontos congelados, pizzas, sorvetes, polpas e vegetais congelados."),
    MERCEARIA_E_MATINAIS(13, "Mercearia e Matinais", "Cereais, achocolatados, geleias, mel e itens para café da manhã.");

    private final int value;
    private final String nome;
    private final String descricao;

    EnumCategoria(int value, String nome, String descricao) {
        this.value = value;
        this.nome = nome;
        this.descricao = descricao;
    }
}