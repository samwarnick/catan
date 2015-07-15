package client.domestic;

import shared.definitions.ResourceType;

public class Trade {

	private Integer woodStatus = 0;
	private Integer brickStatus = 0;
	private Integer sheepStatus = 0;
	private Integer wheatStatus = 0;
	private Integer oreStatus = 0;
	private Integer woodNum = 0;
	private Integer brickNum = 0;
	private Integer sheepNum=0;
	private Integer wheatNum = 0;
	private Integer oreNum = 0;
	private String playerName;
	public Trade(Integer woodStatus, Integer brickStatus, Integer sheepStatus,
			Integer wheatStatus, Integer oreStatus, Integer woodNum,
			Integer brickNum, Integer sheepNum, Integer wheatNum, Integer oreNum,String PlayerName) {
		super();
		this.woodStatus = woodStatus;
		this.brickStatus = brickStatus;
		this.sheepStatus = sheepStatus;
		this.wheatStatus = wheatStatus;
		this.oreStatus = oreStatus;
		this.woodNum = woodNum;
		this.brickNum = brickNum;
		this.sheepNum = sheepNum;
		this.wheatNum = wheatNum;
		this.oreNum = oreNum;
		playerName = PlayerName;
	}
	public Integer getWoodStatus() {
		return woodStatus;
	}
	public void setWoodStatus(Integer woodStatus) {
		this.woodStatus = woodStatus;
	}
	public Integer getBrickStatus() {
		return brickStatus;
	}
	public void setBrickStatus(Integer brickStatus) {
		this.brickStatus = brickStatus;
	}
	public Integer getSheepStatus() {
		return sheepStatus;
	}
	public void setSheepStatus(Integer sheepStatus) {
		this.sheepStatus = sheepStatus;
	}
	public Integer getWheatStatus() {
		return wheatStatus;
	}
	public void setWheatStatus(Integer wheatStatus) {
		this.wheatStatus = wheatStatus;
	}
	public Integer getOreStatus() {
		return oreStatus;
	}
	public void setOreStatus(Integer oreStatus) {
		this.oreStatus = oreStatus;
	}
	public Integer getWoodNum() {
		return woodNum;
	}
	public void setWoodNum(Integer woodNum) {
		this.woodNum = woodNum;
	}
	public Integer getBrickNum() {
		return brickNum;
	}
	public void setBrickNum(Integer brickNum) {
		this.brickNum = brickNum;
	}
	public Integer getSheepNum() {
		return sheepNum;
	}
	public void setSheepNum(Integer sheepNum) {
		this.sheepNum = sheepNum;
	}
	public Integer getWheatNum() {
		return wheatNum;
	}
	public void setWheatNum(Integer wheatNum) {
		this.wheatNum = wheatNum;
	}
	public Integer getOreNum() {
		return oreNum;
	}
	public void setOreNum(Integer oreNum) {
		this.oreNum = oreNum;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
	
	
	
}
