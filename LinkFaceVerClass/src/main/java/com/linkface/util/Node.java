package com.linkface.util;

import java.util.HashMap;
import java.util.Map;

public class Node {

	// 자식 노드
	public Map<Character, Node> childNode = new HashMap<>();
	// 마지막 노드값
	public boolean isLastNode = false;
	// 연결을 위한 노드가 아닌 찾을 값이 들어있는 노드인지 확인
	public boolean isValue = false;
	
}
