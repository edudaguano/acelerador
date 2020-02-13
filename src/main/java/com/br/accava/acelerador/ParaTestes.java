package com.br.accava.acelerador;

import java.util.Iterator;
import java.util.List;

public class ParaTestes {
	
	public static void main(String[] args) {
		String proc = "strPack = \"{CALL YUPROC.YU_PKG_TB_EMPR.spseEmpresa(null, null, null, \" & Left(arrCampos(1), 9) & \", \" & Mid(arrCampos(1), 10, 4) & \", \" & Right(arrCampos(1), 2) & \", null)}\"";
		 proc = proc.substring(1,proc.lastIndexOf(")"));
		char[] arrayzao = proc.toCharArray();
		String juntada = "";
		
		int contadorParenteses = 0;
		int contadorChaves = 0;
		int contadorAspas = 0;
		for(char c : arrayzao) {
			if (c == '\"') {
				if (contadorAspas > 0) {
					contadorAspas--;
					continue;
				} else {
					contadorAspas++;
					continue;
				}
			}
			if (c == '(' || c == '{') {
				contadorParenteses++;
			}
			if (c == ')' || c == '}') {
				contadorParenteses--;
			}
			if (contadorParenteses != 0 || contadorAspas !=0 ) {
				if (c != ',') {
					juntada = juntada + c;
				} else {
					juntada = juntada + "__";
				}
			} else {
				juntada = juntada + c;
			}
			
		}
		
		System.out.println(juntada);
		String[] split = juntada.split(",");
		for (String string : split) {
			System.out.println(string);
		}
		
//		String[] listinha = proc.split(",");
//
//		for (String l : listinha) {
//			System.out.println(l);
//		}
	}

}
