package com.spero.learn;

import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public class CheckCondition {
	
	public static void main(String[] args) {
		try {
			System.out.println("Value ::"+ checkDefaultCondition());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String checkDefaultCondition() throws Exception {
		VTDGen vg = new VTDGen();
        vg.setDoc("<condition><default scid=\"21\"/></condition>".getBytes());
        try {
			vg.parse(true);
	        VTDNav vn = vg.getNav();
	        int count = 0;
	        String scid = null;
	        if(vn.matchElement("condition")){
	            if(vn.toElement(VTDNav.FIRST_CHILD, "default")){
	                do{
	                	count++;
	                    vn.push();
	                    int _curIndx = vn.getCurrentIndex();
	                    if(vn.getAttrCount() != 1 || count > 1){
	                    	scid = null;
	                    	break;
	                    } else{
	                    	 if(vn.toNormalizedString(_curIndx+1).equals("scid")){
		                        	scid = vn.toNormalizedString(_curIndx+2);
		                     }
	                    }
	                    vn.pop();
	                }while(vn.toElement(VTDNav.NEXT_SIBLING));
	            }
	        }
	        return scid;
        }catch (Exception e) {
			System.out.println("Exception occured while parsing xml"+ e);
			throw e;
		}    
	}
}
