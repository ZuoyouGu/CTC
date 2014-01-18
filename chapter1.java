1.1
使用了个别数组，变量。
// assume there are ASCII only.
public static boolean allUnique(String s){
	// initialized as all false
	boolean[] char_set = new boolean[256];
    for(int i=0; i<s.length(); i++){
    	if(char_set[s.charAt(i)]==true) return false;
    	else char_set[s.charAt(i)]=true;
    }
    return true;
}

Time: O(n), Space: O(n)

boolean还是太大了，因此可以使用bit，如果是256 bit,需要32个字节

public static boolean allUnique(String s){
	char[] char_set = new char[32];
	for(int i=0; i<s.length(); i++){
		int val = s.charAt(i);
		int char_index = val/8;
		int char_offset = val%8;
		if(((char_set[char_index]>>(7-char_offset))&0x01)==1) return false;
		else char_set[char_index] = char_set[char_inddex]|(0x01<<(7-char_set));
	}
	return true;
}

1.2
void reverse(char *str){
	int length=0;
	while(str[length]){
		length++;
	}
	for(int i=0; i<length/2; i++){
		char tmp = str[i];
		str[i] = str[length-i];
		str[length-i] = tmp;
	}
}

1.3
/**
 * 思路：检查字符串中的每个字符和目前已经确定的是字符串中的字符是否都不一样，如果都不一样，那么把这个字符移到
 * 已经确认字符串的后面。
 * */
// no additional memory
public static void removeDuplicate(char[] str){
	if(str==null) return;
	if(str.length==1) return;
	int tail = 1;// the terminator for the string
	for(int i=1; i<str.length; i++){
		int j=0;
		for(; j<tail; j++){
			if(str[i]==str[j]) break;
		}
		if(j==tail){// all different
			str[tail] = str[i];
			tail++;
		}
	}
	str[tail] = null;
}

// test cases:
// all duplicate: 8888
// all not duplicate: 1234
// 1233
// 1123
// 1122
// 121212

// with additional memory
public static void removeDuplicates(char[] str){
	boolean[] char_set = new boolean[256];
	// initialize
	for(int i=0; i<256; i++){
		char_set[i] = false;
	}
	int tail = 1;
	char_set[str[0]] = true;
	for(int i=1; i<str.length; i++){
		if(char_set[str[i]]==false){
			char_set[str[i]] = true;
			str[tail] = str[i];
			tail++;
		}
	}
	str[tail]=null;
}

1.4
// anagram:回文同构，每个字母的个数都一样
/**
 * 这里有问题？？？？
 * 只要判断某个字符的个数是否为0，如果出现不为0的，那么就是false的。因为程序开头判断了字符串的长度
 * 所以这样就足够了？
 * */
public static boolean isAnagram(String s1, String s2){
	int[] char_set = new int[256];
	if(s1.length()!=s2.length()) return false;
	// initialized as 0
	for(int i=0; i<s1.length(); i++){
		char_set[s1.charAt(i)]++;
	}
	for(int i=0; i<s2.length(); i++){
		int idx = (int)s2.charAt(i);
		// can this make sure it's right?
		if(char_set[idx]==0) return false;
		char_set[s2.charAt(i)]--;
	}
	return true;
}

1.5
public static String replace(String in){
	String out = new String();
	for(int i=0; i<inChar.length; i++){
		if(in.charAt(i)==' ') out+="\%20";
		else out+=in.charAt(i);
	}
	return out;
}

public static int replace(char[] in, int length){
	int spacecnt=0;
	for(int i=0; i<in.length; i++){
		if(in[i]==' ') spacecnt++;
	}
	nulength = length+spacecnt*2;
	int j=nulength-1;
	for(int i=length-1; i>=0; i--, j--){
		if(in[i]==' ') {
			in[j--]='0';
			in[j--]='2';
			in[j]='\%';//?
		}
		else{
			in[j]=in[i];
		}
	}
	in[nulength]='\0';
	return nulength;
}

1.6
public static void rotate90(int matrix, int n){
	for(int i=0; i<n/2; i++){
		start = i;
		end = n-i-1;
		for(j=start; j<end; j++){
			int tmp = matrix[start][j];
			int offset = j-start;
			matrix[start][j] = matrix[end-offset][start];
			matrix[end-offset][start] = matrix[end][end-offset];
			matrix[end][end-offset] = matrix[j][end];
			matrix[j][end] = tmp;
		}
	}
}

1.7
public static void set0(int matrix, int m, int n){
	boolean[] n_cols = new boolean[n];// initialized as false
	for(int i=0; i<m; i++){
		for(int j=0; j<n; j++){
			if(n_cols[j]==false && matrix[i][j]==0){
				for(int k=0; k<m; k++){
					matrix[k][j]=0;
				}
				for(int k=0; k<n; k++){
					matrix[i][k]=0;
				}
				n_cols[j]=true;
				break;
			}
		}
	}
}

1.8
public static boolean isRotate(String s1, String s2){
	if(s1.length()!=s2.length()) return false;
	String snu = s1+s1;
	return isSubstring(snu, s2);
}
