package com.app.dao;

import java.util.List;
import java.util.Map;

import com.app.bean.DaiDhpTaisyosya;
import com.app.bean.DaiKsyokaiRireki;
import com.app.bean.KeyValuePair;



public interface DaiDhpTaisyosyaDao {

	List<DaiDhpTaisyosya> getDhpTaisyosyaList(Map data);

	DaiDhpTaisyosya getDhpNote(Map data);

	List<KeyValuePair> getDhpNoteList();

	List<DaiKsyokaiRireki> getKsyokaiRireki(Map data);

	void updateDhpTaisyosya(Map data);

	void insertDhpTaisyosyaRireki(Map data);

	List<DaiDhpTaisyosya> getCsvData(Map data);

	List<DaiDhpTaisyosya> getCsvRireData(Map data);
}
