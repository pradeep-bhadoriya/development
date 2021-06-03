// //Code In Python
// //*****************************************************************
// // public class LargestAreaHistogram {
// //     n=int(input())
// // l1=[]
// // for _ in range(n):
// //     l1.append(int(input()))
// nsl=[-1]*n
// nsr=[n]*n
// # nsr[n-1]=n
// # nsl[0]=-1
// st=[]
// # st.append(0)
// #Work for Next Smaller Right
// for i in range(n):
//     while(len(st)>0 and l1[i]<l1[st[-1]]):
//         nsr[st[-1]]=i
//         st.pop()
//     st.append(i)
// while(len(st)>0):
//     nsr[st[-1]]=n
//     st.pop()
// # print(nsr)
// #Work for Next Smaller Left
// for i in range(n-1,-1,-1):
//     while(len(st)>0 and l1[i]<l1[st[-1]]):
//         nsl[st[-1]]=i
//         st.pop()
//     st.append(i)
// while(len(st)>0):
//     nsl[st[-1]]=-1
//     st.pop()
// # print(nsl)
// #Work for FInding Rectangel with help of nsr and nsl
// res=float("-inf")
// for i in range(n):
//     res=max(res,(nsr[i]-nsl[i]-1)*l1[i])
// print(res)


// // }
