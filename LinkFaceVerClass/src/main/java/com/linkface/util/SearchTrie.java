package com.linkface.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class SearchTrie {

	// 필요한 것 추가
	private static Node UserNameRootNode = new Node();

	
	public static void listInsert(List<String> dataList) {		
		dataList.forEach( data -> insert(data) );
	}
	
	
	public static void insert(String str) {
		// 사용 노드 지정
		Node node = UserNameRootNode;
		// 전달받은 문자열로 반복문 사용
		
		for (int i = 0; i < str.length(); i++) {
			// i 번쨰 문자 
			char ch = str.charAt(i);
			// 키가 있다면 키 반환 없다면 새로 키 지정 후 하위 노드로 이동
			node.childNode.putIfAbsent(ch, new Node());
			node = node.childNode.get(ch);
			// 마지막 문자라면 이 노드에 마지막 노드 체크를 하고 리턴
			if (i == str.length() - 1) {
				node.isLastNode = true;
				return;
			}
		}
	}
	
	private static Set<String> getData(Node node,String str) {
		
		Set<String> data = new HashSet<>();
		// 이 노드가 마지막 노드일 경우 문자열을 추가
		if(node.isLastNode) {
			data.add(str);
		}
		// 아닐 경우 자식 노드로 재귀호출
		else {
			for(char ch :node.childNode.keySet()) {
				data.addAll((getData(node.childNode.get(ch),str + ch)));
			}
		}
		// 리턴
		return data;
	}

	
	public static List<String> searchAutocompleteData(String str)
    {
		List<String> result = new ArrayList<>();
		
        Node node = UserNameRootNode;

        for(int i = 0; i < str.length(); i++){
        	
            char ch = str.charAt(i);

            // 해당 문자가 있으면 하위노드로 이동 없으면 return
            if(node.childNode.containsKey(ch)) {
                node = node.childNode.get(ch);
            }
            else { 
            	return result; 
            }
            // 마지막 문자라면 데이터를 재귀함수로 긁어옴
            if(i == (str.length() - 1)) {
            	
                result = List.copyOf(getData(node, str));
                return result;
            }
        }
        return result;
    }
	 

	public static void delete(String str) {

		Node node = UserNameRootNode;

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			// 마지막 문자가 아닌데 하위 노드가 없으면 리턴
			if (!node.childNode.containsKey(ch) && i < (str.length() - 1)) {
				return;
			}
			// 마지막 문자가 아니라면 하위 노드로 이동
			else if (i < (str.length() - 1)) {
				node = node.childNode.get(ch);
			}
			// 마지막 문자일 경우
			else {
				// 하위 노드 꺼냄
				Node child = node.childNode.get(ch);
				// isEmpty 검사 후 삭제
				if (child.childNode.isEmpty()) {
					node.childNode.remove(ch);
				}
				else {
					child.isLastNode = false;
				}
			}
		}
	}

}
