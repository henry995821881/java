package com.app.bean;

import java.io.Serializable;

public class DaiKsyokaiRireki implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String kousinDatetime;
	private String dhpKbn;
	private String dhpNote;
	private String hanteiMoto;

	@Override
	public String toString() {
		return "DaiKsyokaiRireki [kousinDatetime=" + kousinDatetime + ", dhpKbn=" + dhpKbn + ", dhpNote=" + dhpNote
				+ ", hanteiMoto=" + hanteiMoto + "]";
	}

	public String getKousinDatetime() {
		return kousinDatetime;
	}

	public void setKousinDatetime(String kousinDatetime) {
		this.kousinDatetime = kousinDatetime;
	}

	public String getDhpKbn() {
		return dhpKbn;
	}

	public void setDhpKbn(String dhpKbn) {
		this.dhpKbn = dhpKbn;
	}

	public String getDhpNote() {
		return dhpNote;
	}

	public void setDhpNote(String dhpNote) {
		this.dhpNote = dhpNote;
	}

	public String getHanteiMoto() {
		return hanteiMoto;
	}

	public void setHanteiMoto(String hanteiMoto) {
		this.hanteiMoto = hanteiMoto;
	}

}
