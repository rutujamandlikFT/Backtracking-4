import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//Time Complexity : O(k ^ (n/k)) where n = total length, k = avg length of block
//Space Complexity : O(n)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : no

/**
 * Generate character blocks of the string and then apply backtracking for each
 * block. Pick each char for each block and append to the string and push it to
 * the result when blocks are completed. Then remove the last char of the string
 * and push the next char from the block. Finally return the result array.
 *
 */
class Solution {
	List<String> result = new ArrayList<>();

	public String[] expand(String s) {
		List<List<Character>> blocks = new ArrayList<>();
		int i = 0;
		while (i < s.length()) {
			List<Character> block = new ArrayList<>();
			if (s.charAt(i) == '{') {
				i++;
				while (i < s.length() && s.charAt(i) != '}') {
					if (s.charAt(i) != ',')
						block.add(s.charAt(i));
					i++;
				}
			} else {
				block.add(s.charAt(i));
			}
			i++;
			Collections.sort(block);
			blocks.add(block);
		}
		backtrack(blocks, 0, new StringBuilder());
		String[] res = new String[result.size()];
		for (int k = 0; k < res.length; k++) {
			res[k] = result.get(k);
		}
		return res;
	}

	public void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb) {
		if (idx == blocks.size()) {
			result.add(sb.toString());
			return;
		}
		List<Character> block = blocks.get(idx);
		for (int i = 0; i < block.size(); i++) {
			sb.append(block.get(i));
			backtrack(blocks, idx + 1, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
