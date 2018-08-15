package com.RRH.DAC;

import com.RRH.Enums.Categories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// DAC = Data Access Class

public class CategoricalSearchDAC {

    private static Map<String,Categories> allMetaCategories;

    static {
        allMetaCategories = new HashMap<String, Categories>() {{
            put("allergy",Categories.ALLERGY);
            put("medication order", Categories.MEDICATION_ORDER);
        }};
    }

    // Having a drop down on the client side which updates the params on the page based on the category
    // would help implement this method better. For ease of understanding, it has been implemented the way it
    // is right now.
    public static String performRefinedSearch(String ID, String category, String param) throws Exception {
        FhirPersistDAC fhirPersistDAC = new FhirPersistDAC();
        Categories categories = allMetaCategories.get(category);
        String result = null;
        if(categories != null) {
            switch (categories) {
                case ALLERGY: {
                    result = fhirPersistDAC.refinedSearch_Allergy(ID, param);
                    break;
                }
                case MEDICATION_ORDER: {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date givenDate = simpleDateFormat.parse(param);
                    result =  fhirPersistDAC.refinedSearch_MedicationOrder(ID,givenDate);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return result;
    }
}
