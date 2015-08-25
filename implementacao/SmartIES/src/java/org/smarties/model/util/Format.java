/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smarties.model.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Alex
 */
public class Format {

    public Format() {
        super();
    }

    public static String getString(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    public static boolean getBoolean(Object value) {
        try {
            boolean v = (Boolean) value;
            return v;
        } catch (Exception e) {
            Excecoes.erro(e);
            return false;
        }
    }

    public static boolean getBoolean(String value) {
        if (value != null) {
            return value.equals("Sim");
        }
        return false;
    }

    public static int getInt(String value, int defval) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            Excecoes.erro(e);
            return defval;
        }
    }

    public static int getInt(Object value, int defval) {
        try {
            return getInt((String) value, defval);
        } catch (Exception e) {
            Excecoes.erro(e);
            return defval;
        }
    }

    public static int getInt(String value) {
        return getInt(value, 0);
    }

    public static double getDouble(String value, double defval) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            Excecoes.erro(e);
            return defval;
        }
    }

    public static double getDouble(Object value, double defval) {
        try {
            return getDouble((String) value, defval);
        } catch (Exception e) {
            Excecoes.erro(e);
            return defval;
        }
    }

    public static double getMonetario(String value){
        value = value.replace(",", ".");
        return getDouble(value, 0.00);
    }
    
    public static String getCEP(String value){
        value = value.replace("-", "");
        return getString(value);
    }
    
    public static double getDouble(String value) {
        return getDouble(value, 0.0);
    }

    public static int getInt(Object value) {
        return getInt(value, 0);
    }

    public static Calendar getData(String value) {
        if ((value != null) && (!value.isEmpty())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar dataCadastro = Calendar.getInstance();
            try {
                dataCadastro.setTime(sdf.parse(value));
            } catch (Exception e) {
                Excecoes.erro(e);
            }
            return dataCadastro;
        } else {
            return null;
        }
    }

    public static String getData(Calendar value) {
        String sr = "";
        if (value != null) {
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
            sr = s.format(value.getTime());
        }
        return sr;
    }

    public static String getDataEhora(Calendar value) {
        String sr = "";
        if (value != null) {
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
            sr = s.format(value.getTime());
        }
        return sr;
    }
}
