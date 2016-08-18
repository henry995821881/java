package com.app.bean;

import java.io.Serializable;

/**
 * <p>
 * Title: K2+1システム
 * </p>
 * <p>
 * Description: Key-Valueを代表するシンプルなJAVABEAN
 * </p>
 * @author 曹
 * @version $Revision$ $Date$
 */
public final class KeyValuePair implements Serializable {

    /**
     * デフォルト永続化ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * キー
     */
    private String key = null;

    /**
     * 値
     */
    private String value = null;

    /**
     * コンストラクタ
     */
    public KeyValuePair() {

    }

    /**
     * コンストラクタ
     * @param key
     * @param value
     */
    public KeyValuePair(String key, String value) {

        this.key = key;
        this.value = value;
    }

    /**
     * キーを返す
     * @return String
     */
    public String getKey() {

        return key;
    }

    /**
     * 値を返す
     * @return String
     */
    public String getValue() {

        return value;
    }

    /**
     * キーをセット
     * @param key セットするキー
     */
    public void setKey(String key) {

        this.key = key;
    }

    /**
     * 値をセット
     * @param value セットする値
     */
    public void setValue(String value) {

        this.value = value;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final KeyValuePair other = (KeyValuePair) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

}
