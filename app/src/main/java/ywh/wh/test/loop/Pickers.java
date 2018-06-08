package ywh.wh.test.loop;

import java.io.Serializable;

/**
 * 
 * @author zengtao 2015年5月20日下午7:18:14
 *
 */
public class Pickers implements Serializable {

	private static final long serialVersionUID = 1L;

	private String showConetnt;
	private String showId;
	private int color;

	public String getShowConetnt() {
		return showConetnt;
	}

	public String getShowId() {
		return showId;
	}

	public int getColor() {
		return color;
	}

	public Pickers(String showConetnt, String showId,int color) {
		super();
		this.showConetnt = showConetnt;
		this.showId = showId;
		this.color = color;
	}

	public Pickers() {
		super();
	}

}
