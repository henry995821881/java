package com.henry.test.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.henry.test.mybatis.bean.DaiDhpTaisyosya;
import com.henry.test.mybatis.bean.DaiKsyokaiRireki;
import com.henry.test.mybatis.bean.KeyValuePair;

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
