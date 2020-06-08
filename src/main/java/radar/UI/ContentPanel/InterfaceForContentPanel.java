package radar.UI.ContentPanel;

public interface InterfaceForContentPanel {
	
	/**
	 * 页面初始化
	 */
	void init();
	/**
	 * 添加页面组件事件
	 */
	void Action();
	/**
	 * 添加内容面板头部
	 */
	void initContentTop();
	/**
	 * 添加内容面板躯干
	 */
	void initContentBody();
	/**
	 * 添加内容面板底部
	 */
	void initContentFoot();

}
