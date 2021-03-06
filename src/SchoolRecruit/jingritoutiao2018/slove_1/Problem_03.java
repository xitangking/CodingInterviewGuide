package SchoolRecruit.jingritoutiao2018.slove_1;


/********************************************************************************
 * CodingInterviewGuide -- 
 * @version 2017/09/12 22:09
 * @author xtw, xtwyzh@gmail.com,xtwroot.com
 * xtwroot Copyrights (c) 2017. All rights reserved.
 ********************************************************************************/

/*
 *【问题】
 *
 * 产品经理(PM)有很多好的idea，而这些idea需要程序员实现。
 * 现在有N个PM，在某个时间会想出一个 idea，每个 idea 有
 * 提出 时间、所需时间和优先等级。对于一个PM来说，最想实
 * 现的 idea首先考虑优先等级高的，相同的情况下优先所需时
 * 间最小 的，还相同的情况下选择最早想出的，没有 PM 会在
 * 同一时刻提出两个 idea。 同时有M个程序员，每个程序员
 * 空闲的时候就会查看每个PM尚未执行并且最想完成的一个idea,
 * 然后从中挑选出所需时间最小的 一个idea独立实现，如果所
 * 需时间相同则选择PM序号最小的。 直到完成了idea才会重复
 * 上述操作。如果有多个同时处于空闲 状态的程序员，那么他
 * 们会依次进行查看idea的操作。求每个idea实现的时间。
 *
 *【样例输入】
 *
 * 输入第一行三个数N、M、P，分别表示有N个PM，M个程序员，
 * P 个idea。随后有P行，每行有4个数字，分别是PM序号、提出时 间、
 * 优先等级和所需时间。 所有输入数据范围为 [1, 3000]
 *
 *【样例输入】
 *
 * 输出P行，分别表示每个idea实现的时间点。
 *
 *【难度】
 *
 *  帅 ★★★★
 *
 */


public class Problem_03 {

    public static void main(String[] args) {

    }

}

class Idea {
    public int PM;
    public int PutTime;
    public int Pr;
    public int SpendTime;

    public Idea(int pm,int pt,int pr,int st){
        this.PM = pm;
        this.PutTime = pt;
        this.Pr = pr;
        this.SpendTime = st;
    }
}


