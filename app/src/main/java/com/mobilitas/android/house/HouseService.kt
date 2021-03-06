package com.mobilitas.android.house

object HouseService {
    fun getHouses(): List<House> {
        val list = mutableListOf<House>()
        list.add(House("0", -23.6048819, -46.6957957, "Apartamento com 1 dorm, 55m²", 4724.00, "Rua Sansão Alves dos Santos, Brooklin, São Paulo"))
        list.add(House("1", -23.6080094, -46.6938952, "Apartamento com 3 dorms, 136m²", 7517.00, "Rua Arandu, Brooklin, São Paulo"))
        list.add(House("2", -23.6129957, -46.6938146, "Apartamento com 1 dorm, 45m²", 3980.00, "Rua Castilho, Brooklin, São Paulo"))
        list.add(House("3", -23.6105088, -46.6885403, "Apartamento com 1 dorm, 78m²", 3779.00, "Avenida Padre Antônio José dos Santos, Brooklin, São Paulo"))
        list.add(House("4", -23.6119725, -46.6900564, "Apartamento com 3 dorms, 96m²", 3931.00, "Rua Flórida, Brooklin, São Paulo"))
        list.add(House("5", -23.6105088, -46.6885403, "Apartamento com 2 dorms, 87m²", 3970.00, "Avenida Padre Antônio José dos Santos, Brooklin, São Paulo"))
        list.add(House("6", -23.6066715, -46.6966123, "Apartamento com 2 dorms, 65m²", 3176.00, "Rua José Muniz dos Santos, Brooklin, São Paulo"))
        list.add(House("7", -23.6043725, -46.6948472, "Apartamento com 2 dorms, 70m²", 2889.00, "Avenida Engenheiro Luiz Carlos Berrini, Brooklin, São Paulo"))
        list.add(House("8", -23.5702302, -46.6953211, "Studio com 1 dorm, 27m²", 3715.00, "Rua Diogo Moreira, Pinheiros, São Paulo"))
        list.add(House("9", -23.5639426, -46.6987058, "Apartamento com 1 dorm, 40m²", 6572.00, "Rua Padre Carvalho, Pinheiros, São Paulo"))
        list.add(House("10", -23.5611261, -46.6914638, "Casa com 3 dorms, 125m²", 4588.00, "Rua Mourato Coelho, Pinheiros, São Paulo"))
        list.add(House("11", -23.5715483, -46.6983344, "Studio com 1 dorm, 36m²", 4541.00, "Av. Eusébio Matoso, Pinheiros, São Paulo"))
        list.add(House("12", -23.5610076, -46.6854482, "Apartamento com 1 dorm, 25m²", 3160.00, "Rua Teodoro Sampaio, Pinheiros, São Paulo"))
        list.add(House("13", -23.5639426, -46.6987058, "Apartamento com 1 dorm, 40m²", 5638.00, "R. Padre Carvalho - Pinheiros, São Paulo - Sp, Brasil, Pinheiros, São Paulo"))
        list.add(House("14", -23.5652499, -46.7014449, "Casa com 2 dorms, 216m²", 3171.00, "Rua Sumidouro, Pinheiros, São Paulo"))
        list.add(House("15", -23.4962104, -46.6346021, "Apartamento com 2 dorms, 70m²", 2526.09, "Rua Marechal Hermes da Fonseca, Santana, São Paulo"))
        list.add(House("16", -23.50076, -46.6280968, "Apartamento com 1 dorm, 42m²", 2944.00, "Rua Voluntários da Pátria, Santana, São Paulo"))
        list.add(House("17", -23.4957084, -46.6308798, "Apartamento com 4 dorms, 180m²", 7328.00, "Rua Doutor João Batista Soares de Faria, Santana, São Paulo"))
        list.add(House("18", -23.4979889, -46.6270389, "Apartamento com 3 dorms, 200m²", 5616.00, "Rua Nunes Garcia, Santana, São Paulo"))
        list.add(House("19", -23.4973482, -46.6242755, "Apartamento com 1 dorm, 42m²", 2257.00, "Rua Zuquim, Santana, São Paulo"))
        list.add(House("20", -23.4961045, -46.6225534, "Casa com 4 dorms, 322m²", 6599.00, "Rua Damião Simões, Santana, São Paulo"))
        list.add(House("21", -23.5626676, -46.661106, "Apartamento com 3 dorms, 160m²", 4474.00, "Rua Peixoto Gomide, Consolação, São Paulo"))
        list.add(House("22", -23.5598054, -46.655247, "Apartamento com 1 dorm, 58m²", 4262.00, "Rua Itapeva, Bela Vista, São Paulo"))
        list.add(House("23", -23.5597777, -46.6573399, "Apartamento com 2 dorms, 127m²", 4792.00, "Rua Engenheiro Monlevade, Consolação, São Paulo"))
        list.add(House("24", -23.5556248, -46.6516519, "Apartamento com 2 dorms, 60m²", 2876.00, "Avenida Nove de Julho, Bela Vista, São Paulo"))
        list.add(House("25", -23.5794067, -46.646832, "Apartamento com 1 dorm, 43m²", 3490.00, "Rua Joinville, Paraíso, São Paulo"))
        list.add(House("26", -23.5694306, -46.6528945, "Apartamento com 1 dorm, 37m²", 3219.00, "Avenida Brigadeiro Luís Antônio, Paraíso, São Paulo"))
        list.add(House("27", -23.573005, -46.6495738, "Apartamento com 3 dorms, 170m²", 5816.00, "Rua Doutor Rafael de Barros, Paraíso, São Paulo"))
        list.add(House("28", -23.5755104, -46.6582758, "Apartamento com 1 dorm, 31m²", 4604.00, "Rua Salto, Paraíso, São Paulo"))
        list.add(House("29", -23.5794067, -46.646832, "Apartamento com 1 dorm, 51m²", 2911.00, "Rua Eça de Queiróz, Vila Mariana, São Paulo"))
        list.add(House("30", -23.5729108, -46.6528287, "Apartamento com 2 dorms, 120m²", 5174.00, "Rua Otávio Nébias, Paraíso, São Paulo"))
        list.add(House("31", -23.5923958, -46.6830733, "Studio com 1 dorm, 52m²", 6622.00, "Rua Ministro Jesuíno Cardoso, Vila Olímpia, São Paulo"))
        list.add(House("32", -23.5931217, -46.6837197, "Apartamento com 1 dorm, 32m²", 6035.00, "Rua Coronel Joaquim Ferreira Lobo, Vila Olímpia, São Paulo"))
        list.add(House("33", -23.5848364, -46.6777537, "Apartamento com 1 dorm, 70m²", 4852.00, "Rua Bandeira Paulista, Itaim Bibi, São Paulo"))
        list.add(House("34", -23.587439, -46.6758302, "Studio com 1 dorm, 79m²", 5017.00, "Rua Benedito Lapin, Vila Olímpia, São Paulo"))
        list.add(House("35", -23.5815706, -46.6838131, "Apartamento com 3 dorms, 147m²", 6520.00, "Rua André Fernandes, Itaim Bibi, São Paulo"))
        list.add(House("36", -23.5814246, -46.6792123, "Apartamento com 1 dorm, 60m²", 6914.00, "Rua Pedroso Alvarenga, Itaim Bibi, São Paulo"))

        return list.toList()
    }
}