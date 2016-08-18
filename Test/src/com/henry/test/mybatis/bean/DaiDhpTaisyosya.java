package com.henry.test.mybatis.bean;

import java.io.Serializable;

public class DaiDhpTaisyosya implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String knsnNo;
	private String kanyuNo2;
	private String fuyouNo2;
	private String kigou;
	private String bangou;
	private String zokugaraNm;
	private String kojinNm;
	private String sex;
	private String age;
	private String org;
	private String note;
	private String dhpKbn;

	private String tanjoubi;
	private String nendo;

	private String rirekiNo;
	private String kousinId;
	private String kousinDatetime;

	public String getRirekiNo() {
		return rirekiNo;
	}

	public void setRirekiNo(String rirekiNo) {
		this.rirekiNo = rirekiNo;
	}

	public String getKousinId() {
		return kousinId;
	}

	public void setKousinId(String kousinId) {
		this.kousinId = kousinId;
	}

	public String getKousinDatetime() {
		return kousinDatetime;
	}

	public void setKousinDatetime(String kousinDatetime) {
		this.kousinDatetime = kousinDatetime;
	}

	public String getTanjoubi() {
		return tanjoubi;
	}

	public void setTanjoubi(String tanjoubi) {
		this.tanjoubi = tanjoubi;
	}

	public String getNendo() {
		return nendo;
	}

	public void setNendo(String nendo) {
		this.nendo = nendo;
	}

	public String getKnsnNo() {
		return knsnNo;
	}

	public void setKnsnNo(String knsnNo) {
		this.knsnNo = knsnNo;
	}

	public String getDhpKbn() {
		return dhpKbn;
	}

	public void setDhpKbn(String dhpKbn) {
		this.dhpKbn = dhpKbn;
	}

	public String getKanyuNo2() {
		return kanyuNo2;
	}

	public void setKanyuNo2(String kanyuNo2) {
		this.kanyuNo2 = kanyuNo2;
	}

	public String getFuyouNo2() {
		return fuyouNo2;
	}

	public void setFuyouNo2(String fuyouNo2) {
		this.fuyouNo2 = fuyouNo2;
	}

	public String getKigou() {
		return kigou;
	}

	public void setKigou(String kigou) {
		this.kigou = kigou;
	}

	public String getBangou() {
		return bangou;
	}

	public void setBangou(String bangou) {
		this.bangou = bangou;
	}

	public String getZokugaraNm() {
		return zokugaraNm;
	}

	public void setZokugaraNm(String zokugaraNm) {
		this.zokugaraNm = zokugaraNm;
	}

	public String getKojinNm() {
		return kojinNm;
	}

	public void setKojinNm(String kojinNm) {
		this.kojinNm = kojinNm;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@Override
	public String toString() {
		return "DaiDhpTaisyosya {knsnNo=" + knsnNo + ", kanyuNo2=" + kanyuNo2 + ", fuyouNo2=" + fuyouNo2 + ", kigou="
				+ kigou + ", bangou=" + bangou + ", zokugaraNm=" + zokugaraNm + ", kojinNm=" + kojinNm + ", sex=" + sex
				+ ", age=" + age + ", org=" + org + ", note=" + note + ", dhpKbn=" + dhpKbn + ", tanjoubi=" + tanjoubi
				+ ", nendo=" + nendo + ", rirekiNo=" + rirekiNo + ", kousinId=" + kousinId + ", kousinDatetime="
				+ kousinDatetime + "}";
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
