package com.btssio.models.utils;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<String, Double>> {

    public static class AdaptedMap {
        @XmlElement(name = "ReductionFamiliale")
        public List<ReductionFamiliale> reductionFamiliales = new ArrayList<>();
    }

    public static class ReductionFamiliale {
        @XmlElement(name = "Categorie")
        public String categorie;
        @XmlElement(name = "Type")
        public String type;
        @XmlElement(name = "Montant")
        public Double montant;
    }

    @Override
    public Map<String, Double> unmarshal(AdaptedMap adaptedMap) throws Exception {
        Map<String, Double> map = new HashMap<>();
        for (ReductionFamiliale reductionFamiliale : adaptedMap.reductionFamiliales) {
            String key = reductionFamiliale.categorie + "," + reductionFamiliale.type;
            map.put(key, reductionFamiliale.montant);
        }
        return map;
    }

    @Override
    public AdaptedMap marshal(Map<String, Double> map) throws Exception {
        AdaptedMap adaptedMap = new AdaptedMap();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            ReductionFamiliale reductionFamiliale = new ReductionFamiliale();
            String[] parts = entry.getKey().split(",");
            reductionFamiliale.categorie = parts[0];
            reductionFamiliale.type = parts[1];
            reductionFamiliale.montant = entry.getValue();
            adaptedMap.reductionFamiliales.add(reductionFamiliale);
        }
        return adaptedMap;
    }
}
