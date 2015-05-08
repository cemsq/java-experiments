/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cesarmora
 */
public class ExecutionBlockExtractor {
    
    public ExecutionBlockExtractor() {
        
    }
    
    public String extract(String text) {        
        String iniTag = "<ExecutionBlock";
        String endTag = "</ExecutionBlock>";
                
        int ini = text.indexOf(iniTag);
        int end = text.indexOf(endTag);
                
        if (ini >= end) {
            return "";
        }
        end += endTag.length();
        
        return text.substring(ini, end);
    }
}
