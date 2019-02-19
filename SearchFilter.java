
package CrazyClients;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class SearchFilter {
    private boolean themeSearch, 
            ageSearch, 
            actualOnly, 
            leftSelected,
            rightSelected,
            useAdditionalFilters;
    private String nameString = "";
    private int minAge, maxAge;
    private List<String> themes = new ArrayList<>();
    private final ArrayList<String> searchFieldsNames = new ArrayList<>();
   // public final static SearchFilter ALL_CLIENTS = new SearchFilter(false);
    /*
    * missingFields[0] = recommenderSearch
    * missingFields[1] = phoneSearch
    * missingFields[2] = responseSearch_1
    * missingFields[3] = responseSearch_2
    * missingFields[4] = responseSearch_3
    * missingFields[5] = responseSearch_4
    * missingFields[6] = responseSearch_5
    * missingFields[7] = targetSearch
    * missingFields[8] = medicineSearch
    */
    private final boolean [] missingFields = new boolean[9];
    private int [] themesIndexes = {};
    private final String []  missingFieldsNames =  {
        "RECOMENDER", 
        "PHONE_NUMBER",
        "RESPONSE_1",                            
        "RESPONSE_2",
        "RESPONSE_3",
        "RESPONSE_4",
        "RESPONSE_5",
        "CORRECTION_TARGET",
        "MEDICINE" };
       
    public SearchFilter(boolean actualOnly) {
        this.themeSearch  = false;
        this.ageSearch    = false;
        this.actualOnly   = actualOnly;
        this.leftSelected = true;
        for (int i = 0; i < 9; i++) 
            missingFields[i] = false;
        this.minAge = 0;
        this.maxAge = -999;        
    }

    
    public String createQuery(){
        StringBuilder stb = new StringBuilder(actualOnly ? 
                 "Select CLIENT_ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, IS_ACTUAL, DATE_LAST "
                         + "from Clients "
                         + "WHERE IS_ACTUAL = 1 AND LAST_NAME LIKE '%"+nameString+"%' " :
                 "Select CLIENT_ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, IS_ACTUAL, DATE_LAST "
                         + "from Clients WHERE LAST_NAME LIKE '%"+nameString+"%' ");
            if(useAdditionalFilters){
                if(leftSelected){
                    if(themeSearch && !themes.isEmpty()){
                        if(themes.size()==1) {
                            stb.append("AND RESPONSE_MAIN = '");
                            stb.append(themes.get(0));
                            stb.append("' ");
                        }
                        else{
                            stb.append("AND (");
                            for (int i = 0; i < themes.size()-1; i++) {
                                stb.append("RESPONSE_MAIN = '");
                                stb.append(themes.get(i));
                                stb.append("' OR ");
                            }
                            stb.append("RESPONSE_MAIN = '");
                            stb.append(themes.get(themes.size()-1));
                                stb.append("') ");
                        }
                    }
                    if(ageSearch){
                        stb.append("AND DATE_OF_BIRTH BETWEEN DATEADD(yy, ");
                        stb.append(String.valueOf(maxAge));
                        stb.append(",  GETDATE()) AND DATEADD(yy, ");
                        stb.append(String.valueOf(minAge));
                        stb.append(",  GETDATE()) ");
                    }
                }
                if(rightSelected){
                    searchFieldsNames.clear();
                    for (int i = 0; i < 9; i++) 
                        if(missingFields[i] == true) {
                            searchFieldsNames.add(missingFieldsNames[i]);
                        }
                    switch(searchFieldsNames.size()){
                        case 0:
                            break;
                        case 1:
                            stb.append("AND ");
                            stb.append(searchFieldsNames.get(0));
                            stb.append(" LIKE '' OR ");
                            stb.append(searchFieldsNames.get(0));
                            stb.append(" LIKE '%     ' ");
                            break;
                        default:
                            stb.append("AND (");
                            for (int i = 0; i < searchFieldsNames.size()-1; i++) {
                                stb.append(searchFieldsNames.get(i));
                                stb.append(" LIKE '' OR ");
                                stb.append(searchFieldsNames.get(i));
                                stb.append(" LIKE '%     ' OR ");

                            }
                            stb.append(searchFieldsNames.get(searchFieldsNames.size()-1));
                            stb.append(" LIKE '' OR ");
                                stb.append(searchFieldsNames.get(searchFieldsNames.size()-1));
                                stb.append(" LIKE '%     ') ");
                            break;
                    }
                }
            }
        stb.append("ORDER BY LAST_NAME, FIRST_NAME");
        System.out.println(stb.toString());
        return  stb.toString();
    }

    public void setMinAge(int minAge)                           { this.minAge = -minAge;                }
    public void setMaxAge(int maxAge)                           { this.maxAge = -maxAge;                }
    public void setThemes(List<String> themes)                  { this.themes = themes;                 }
    public void setAgeSearch(boolean ageSearch)                 { this.ageSearch = ageSearch;           }
    public void setNameString(String nameString)                { this.nameString = nameString;         }
    public void setActualOnly(boolean actualOnly)               { this.actualOnly = actualOnly;         }
    public void setPhoneSearch(boolean phoneSearch)             { missingFields[1] = phoneSearch;       }
    public void setThemeSearch(boolean themeSearch)             { this.themeSearch = themeSearch;       }
    public void setTargetSearch(boolean targetSearch)           { missingFields[7] = targetSearch;      }
    public void setThemesIndexes(int[] themesIndexes)           { this.themesIndexes = themesIndexes;   }
    public void setMedicineSearch(boolean medicineSearch)       { missingFields[8] = medicineSearch;    }
    public void setResponseSearch_1(boolean responseSearch_1)   { missingFields[2] = responseSearch_1;  }
    public void setResponseSearch_2(boolean responseSearch_2)   { missingFields[3] = responseSearch_2;  }
    public void setResponseSearch_3(boolean responseSearch_3)   { missingFields[4] = responseSearch_3;  }
    public void setResponseSearch_4(boolean responseSearch_4)   { missingFields[5] = responseSearch_4;  }
    public void setResponseSearch_5(boolean responseSearch_5)   { missingFields[6] = responseSearch_5;  }
    public void setRecommenderSearch(boolean recommenderSearch) { missingFields[0] = recommenderSearch; }
    public void setUseAdditionalFilters(boolean use)            { this.useAdditionalFilters = use;      }
    
   
    public void setLeftSelected(boolean leftSelected) {  
        this.leftSelected = leftSelected;    
        this.rightSelected = !leftSelected;
    }
    
    public int     getMinAge()              { return -minAge;              }
    public int     getMaxAge()              { return -maxAge;              }
    public boolean isAgeSearch()            { return ageSearch;            }
    public boolean isPhoneSearch()          { return missingFields[1];     }
    public boolean isThemeSearch()          { return themeSearch;          }
    public boolean isTargetSearch()         { return missingFields[7];     }
    public boolean isLeftSelected()         { return leftSelected;         }
    public boolean isMedicineSearch()       { return missingFields[8];     }
    public int[]   getThemesIndexes()       { return themesIndexes;        }
    public boolean isResponseSearch_1()     { return missingFields[2];     }
    public boolean isResponseSearch_2()     { return missingFields[3];     }
    public boolean isResponseSearch_3()     { return missingFields[4];     }
    public boolean isResponseSearch_4()     { return missingFields[5];     }
    public boolean isResponseSearch_5()     { return missingFields[6];     }
    public boolean isRecommenderSearch()    { return missingFields[0];     }
    public boolean isUseAdditionalFilters() { return useAdditionalFilters; }
    
}
