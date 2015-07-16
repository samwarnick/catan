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
	private int sender;
	private int receiver;
	public Trade(Integer woodNum,Integer brickNum, Integer sheepNum, Integer wheatNum, Integer oreNum,int sender,int receiver) {
		super();
		this.woodStatus = woodNum.compareTo(0);
		this.brickStatus = brickNum.compareTo(0);
		this.sheepStatus = sheepNum.compareTo(0);
		this.wheatStatus = wheatNum.compareTo(0);
		this.oreStatus = oreNum.compareTo(0);
		this.woodNum = woodNum;
		this.brickNum = brickNum;
		this.sheepNum = sheepNum;
		this.wheatNum = wheatNum;
		this.oreNum = oreNum;
		this.sender = sender;
		this.receiver = receiver;
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
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	
	
	
	
	
	
}
