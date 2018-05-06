package comp_431.privacytracking.database;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;


public class AListBooleansTypeConvertors {

    @TypeConverter
    public static Integer BooleanALTOInteger(ArrayList<Boolean> booleanslist ){
        Integer result = 0;

        for(int i=0;i<booleanslist.size();i++){ // 1=True 2=False
            if(booleanslist.get(i)){
                result +=1;
            }
            else{
                result+=2;
            }
            result = result*10;
        }
        return result;
    }

    @TypeConverter
    public static ArrayList<Boolean> IntegerTOBooleanAL(Integer integerlist ){
        ArrayList<Boolean> booleansListInverted = new ArrayList<Boolean>();
        ArrayList<Boolean> booleansList = new ArrayList<Boolean>();
        Integer size=0;
        Integer copy = integerlist;
        while(copy > 2){
            copy = copy/10;
            size++;
        }
        while(integerlist > 2 ){
            if(integerlist%10 == 1){
                booleansListInverted.add(true);
            }
            else {
                booleansListInverted.add(false);
            }
        }
        for(int i=0; i< booleansListInverted.size();i++){
            booleansList.add(booleansListInverted.get(size));
            size--;
        }
        return booleansList;
    }
}
