package test;

import java.util.ArrayList;
import java.util.List;

public class Sort {

	
	//冒泡排序
	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			
			for (int j = 1; j < arr.length-i; j++) {
				if(arr[j-1]>arr[j]) {//从小到大     条件反着就是从大到小
					int temp =arr[j];
					arr[j]=arr[j-1];
					arr[j-1]=temp;
				}
			}
		}
	}
	//选择排序
	public static void selectSort(int[] arr) {
		//每次循环，比较次数和冒泡一样，但是执教换一次，提高效率
		for (int i = 0; i < arr.length; i++) {
			int lowIndex=i;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[lowIndex]<arr[j]) {//从大到小     条件反着就是从小到大  跟冒泡一样的道理
					lowIndex=j;
				}
				
			}
			//将当前第一个元素与他后面序列中的最大的元素交换，也就是将最大的元素放在最前方
			int temp=arr[i];
			arr[i]=arr[lowIndex];
			arr[lowIndex]=temp;
		}
	}
	
	//插入排序--由大到小
	public static void insertSort(int[] arr) {
		int j;
		int key;
		for (int i = 0; i < arr.length; i++) {
			key=arr[i];//key就是我们每次新摸到的牌，现在需要找到插入的位置并空出位置
			j=i-1;
			while(j>=0&&arr[j]<key) {//将已排序好的所有比key小的元素右移一个位置，空出一个位置，方便key插入
				arr[j+1]=arr[j];
				j--;
			}
			arr[j+1]=key;//arr[j+1]就是空出位置，将之前的数插入
		}
		
	}
	
	//希尔排序 --由大到小
	public static void shellSort(int[] arr) {
		int size=arr.length;
		//这层for是分组
		for(int gap = size/2;gap>=1;gap/=2) {
			//这层for是对没组进行插入排序
			for (int i = gap; i < size; i++) {
				int temp=arr[i];
				int k=i-gap;
				while(k>=0&&arr[k]<temp) {
					arr[k+gap]=arr[k];
					k=k-gap;
				}
				arr[k+gap]=temp;
			}
		}
	}
	
	//快速排序----由大到小    找一个中间数 左边的大于它  右边的小于它 这就是基准数
	public static void quick_sort(int arr[],int left,int right) {
		//改算法选取第一个数作为基准数，如果想用中间数为基准数，那么将第一个数和中间数交换一次即可
		if(left<right) {
			//Swap(s[left],s[(right+left)/2]);//将这个中间数和第一个数交换
			int i =left,j=right,x=arr[left];
			while(i<j) {
				//从右向左找第一个大于x的数
				while(i<j&&arr[j]<x) {//注意：如果进入循环，表示没有找到，继续找
					j--;//如果没找到，那么j--，继续找，找到则跳出循环
				}
				if(i<j) {
					//当从右向左找到后,i要加1
					arr[i++]=arr[j];//核心是i++
				}
				//从左向右找第一个小于等于x的数
				while(i<j&&arr[i]>=x) {//注意：如果进入循环，表示没有找到，继续找
					i++;//如果没找到，i++，继续找，找到则跳出循环
				}
				if(i<j) {
					//当从左向右找到后j要减1
					arr[j--]=arr[i];//核心是j--
				}
			}
			arr[i]=x;//将基准数填入空位置
			quick_sort(arr,left,i-1);//递归调用
			quick_sort(arr,i+1,right);
			
		}
		
	}
	
	//归并排序----由大到小
	public static void mergesort(int[] a,int first,int last,int [] temp) {
		if(first<last) {
			int mid=(first + last) / 2;
			mergesort(a, first, mid, temp);//左边有序
			mergesort(a, mid+1, last, temp);//右边有序
			mergearray(a, first, mid,last, temp);//再将二哥有序数列合并
		}
	}
	//将二哥有序数列a[first ... mid]和[mid ...last]合并
	private static void mergearray(int[] a, int first, int mid, int last, int[] temp) {
		// TODO Auto-generated method stub
		int i = first , j = mid+1;
		int m = mid, n = last;
		int k = 0;
		while(i <= m && j <= n) {
			if(a [i] >= a [j]) {//由大到小
				temp[k++] = a[i++];
			}else {
				temp[k++] = a[j++];
			}
		}
		while(i <= m) {//将剩下的A数组中的数填充到C中，如果有的话
			temp[k++] = a[i++];
		}
		while(j <= n) {//将剩下的B数组中的数填充到C中， 如果有的话
			temp[k++] = a[j++];
		}
		for (i = 0; i < k; i++) {
			a[first + i] = temp[i];
		}
	}
	
	/**
	 *  要进行基数排序的数组
	 * @param 
	 */
	
	public static void radixSort(int [] arrays) {
		/**
		 * 这个没用
		 * 为了能够计算对正负数进行排序，需要将正数和负数分成两个数组
		 * 负数数组进行全部取绝对值，然后进行基数排序，拍好后，全部位置反向，符号变为符
		 * 正数数组不应处理
		 * 最后两个排好序的数组进行合并即可，感觉好麻烦啊！！！！！！
		 */
		//计算有多少个负数
		int neg_count = 0;
		for (int i = 0; i < arrays.length; i++) {
			if(arrays[i] < 0) {
				neg_count++;
			}
		}
		//分成两个数组
		int[] neg_list = new int[neg_count];
		int j = 0,k = 0;
		int[] pos_list = new  int[arrays.length - neg_count];
		for (int i = 0; i < arrays.length; i++) {
			if(arrays[i] < 0) {
				neg_list[j++] = Math.abs(arrays[i]);
			}else {
				pos_list[k++] = arrays[i];
			}
		}
		//对负数数组进行基数排序
		//radixSort_part(neg_list);
		//反转
		for (int startIndex = 0, endIndex = neg_list.length-1;startIndex < endIndex;startIndex++,endIndex--) {
			int temp = neg_list[startIndex];
			neg_list[startIndex] = neg_list[endIndex];
			neg_list[endIndex] = temp;
		}
		//变为负数
		/*for (int i = 0; i < neg_list.length; i++) {
			neg_list[i] =0-neg_list[i];
		}*/
		//对正数数组进行基数排序
		radixSort_part(pos_list);
		//两个数组合并
		//System.arraycopy( neg_list, 0, arrays, 0, neg_count);
		System.arraycopy( pos_list, 0, arrays, neg_count, arrays.length - neg_count);
	} 
	/**
	 * 这个函数只能进行全部是正数的基数排序    
	 */
	private static void radixSort_part(int[] array) {
		int max, time;
		//确定最大的数有几位数，确定排序的次数
		max = array[0];
		for (int i =  array.length-1; i>0 ; i--) {
			if(max < array[i]) {
				max = array[i];
			}
		}
		time= 0;
		while(max > 0) {
			max /= 10;
			time++;
		}
		//建立10个数组
		List<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>(10);
		for (int i = 0; i < 10; i++) {
			ArrayList<Integer> queue1 = new ArrayList<Integer>();
			queue.add(queue1);
		}
		
		//分别进行time次分配和收集数组，根据不同的位数进行分配到数组中， 在收集到原来的数组中
		for (int i = 0; i < time; i++) {
			for (int j = 0; j < array.length; j++) {
				//Math.pow(10,i+1)表示10的i+1次幂
				int index = (int)(array[j] % Math.pow(10, i + 1) / Math.pow(10, i));
				ArrayList<Integer> queue2=queue.get(index);
				queue2.add(array[j]);
				queue.set(index, queue2);
			}
			//收集完了之后进行分配
			int count = 0;
			for (int k = 0; k < 10; k++) {
				while(queue.get(k).size() > 0) {
					ArrayList<Integer> queue3 = queue.get(k);
					array[count] = queue3.remove(0);
					count++;
				}
			}
		}
		
		
	}
	
	
	/**
	 * 堆排序 ---由大到小（建立最小堆）
	 * 堆排序 ---有小到大（建立最大堆）
	 * 虽然感觉好像不太对劲，按照常理 由大到小，应该是建立最大堆，但是就是该建立最小堆
	 * 主要原因是我们没有开辟新的空间，而是在原始空间基础上完成的，需要反向赋值
	 * 
	 * @param heapArray
	 */
	public static void headSort(int [] heapArray) {
		int size = heapArray.length;
		int current = size;
		//初始化将数据调整为最小堆结构
		//注意： j=size/2-1本质上没有区别，有些人写成了后面的形式
		//但实际上前者更好，size/2-1是从底部开始，第一个有子节点的节点，然后从后面开始进行调整，构造最小堆
		for (int j = size/2-1;j>=0;j--) {
			//j表示调整j节点及其子孙节点为堆结构，其余节点不管
			trickleDown(j,heapArray,current);
		}
		//堆排序
		for (int i = 0; i < heapArray.length; i++) {
			//最小堆：root永远是最小的
			//最大堆：root永远是最大的
			int root = heapArray[0];
			heapArray[0] = heapArray[--current];
			//沉降法调整为堆结构，0表示调整整个树
			trickleDown(0, heapArray, current);
			//反向赋值
			heapArray[i] = root;
		}
	}
	
	
	
	//目前里面实现的是|：最小堆-由大到小排序
	//如果需要有小到大排序---最大堆，那么改两个地方">"改为"<"即可
	private static void trickleDown(int index, int[] heapArray, int current) {
		// TODO Auto-generated method stub
		int largerChild;
		int top = heapArray[index];//保存目前节点-是父节点
		while(index<current/2) {//判断是否有孩子节点， 如果没有，不需要调整
			int leftChild = 2*index+1;//左孩子节点下标
			int rightChild = leftChild+1;//右孩子节点下标
			//最小堆：找出左右孩子节点中，最小的那个
			//最大堆：找出左右孩子节点中，最大的那个
			if(rightChild<current&&heapArray[leftChild]>heapArray[rightChild]) {
				largerChild = rightChild;
			}else {
				largerChild = leftChild;
			}
			
			//最小堆：如果父节点比孩子节点中最小的那个还小，那么不需要调整，否则孩子节点和父节点交互
			//最大堆：如果父节点比孩子节点中最大的那个还大，那么不需要调整，否则孩子节点和父节点交互
			if(top<= heapArray[largerChild]) {
				break;
			}
			//孩子节点和父节点交换位置
			heapArray[index] = heapArray[largerChild];
			index = largerChild;//下一步运算需要
		}
		//整个堆调整完成，将当前节点填入空位置，完成一个节点的最大堆或者最小堆操作
		heapArray[index] = top;
	}
	/**
	 * main方法
	 * @param args
	 */
	public static void main(String[] args) {
		int [] arr= {5,8,9,20,16,55,3};
		//bubbleSort(arr);//冒泡排序
		//selectSort(arr)//选择排序
		//insertSort(arr)//插入排序
		//shellSort(arr);//希尔排序
		//quick_sort(arr, 0, arr.length-1);//快速排序
		/*//归并排序
		int[] tmp=new int[arr.length];
		mergesort(arr, 0, arr.length-1, tmp);*/
		
		/*//基数排序
		radixSort(arr);
		*/
		//堆排序
		headSort(arr);
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
	}
	
	
	
}
