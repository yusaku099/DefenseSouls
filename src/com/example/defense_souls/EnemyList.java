package com.example.defense_souls;

public class EnemyList {
    private int[] no;
    private int[] appear;
    private int[] cost;
    private int typeMax;
    
    public EnemyList(int basetype) {
        switch (basetype) {
            // 大平原　敵セット
        case Common.ENEMYBASE_TYPE001:
            typeMax = 7;
            no = new int[]      {1, 2,  21, 32, 41, 42, 43};
            appear = new int[]  {0, 9,  3,  9,  1,  12, 25};
            cost = new int[]    {1, 7,  2,  7,  1,  8,  16};
            break;
        
            // 三つの道標　敵セット
        case Common.ENEMYBASE_TYPE011:
            typeMax = 5;
            no = new int[]      {1, 2,  21, 32, 41};
            appear = new int[]  {0, 8,  3,  7,  0};
            cost = new int[]    {1, 5,  2,  5,  1};
            break;
        case Common.ENEMYBASE_TYPE012:
            typeMax = 5;
            no = new int[]      {51, 52, 21, 31, 32, 42};
            appear = new int[]  {0,  8,  3,  0,  8,  8};
            cost = new int[]    {1,  5,  2,  1,  5,  5};
            break;
        case Common.ENEMYBASE_TYPE013:
            typeMax = 5;
            no = new int[]      {1, 2,  21, 32, 41};
            appear = new int[]  {0, 8,  3,  7,  0};
            cost = new int[]    {1, 5,  2,  5,  1};
            break;
            
            // 最前線　敵セット
        case Common.ENEMYBASE_TYPE021:
            typeMax = 3;
            no = new int[]      {1, 2,  3};
            appear = new int[]  {0, 8,  16};
            cost = new int[]    {1, 5,  10};
            break;
        case Common.ENEMYBASE_TYPE022:
            typeMax = 2;
            no = new int[]      {21, 22};
            appear = new int[]  {1,  9};
            cost = new int[]    {1,  6};
            break;
        case Common.ENEMYBASE_TYPE023:
            typeMax = 3;
            no = new int[]      {1, 2,  3};
            appear = new int[]  {3, 10, 18};
            cost = new int[]    {1, 5,  10};
            break;
        case Common.ENEMYBASE_TYPE024:
            typeMax = 2;
            no = new int[]      {11,    12};
            appear = new int[]  {1,     10};
            cost = new int[]    {1,     5};
            break;

            // 略奪されし鉱山　敵セット
        case Common.ENEMYBASE_TYPE031:
            typeMax = 5;
            no = new int[]      {51, 52, 21, 31, 32, 42};
            appear = new int[]  {0,  8,  3,  0,  8,  8};
            cost = new int[]    {1,  5,  2,  1,  5,  5};
            break;
        case Common.ENEMYBASE_TYPE032:
            typeMax = 5;
            no = new int[]      {51, 52, 21, 31, 32, 42};
            appear = new int[]  {0,  8,  3,  0,  8,  8};
            cost = new int[]    {1,  5,  2,  1,  5,  5};
            break;
            
            // 結晶洞窟　敵セット
        case Common.ENEMYBASE_TYPE041:
            typeMax = 3;
            no = new int[]      {31, 32, 33};
            appear = new int[]  {0,  8,  15};
            cost = new int[]    {1,  5,  10};
            break;
        case Common.ENEMYBASE_TYPE042:
            typeMax = 3;
            no = new int[]      {31, 32, 33};
            appear = new int[]  {3,  11, 18};
            cost = new int[]    {1,  5,  10};
            break;
        case Common.ENEMYBASE_TYPE043:
            typeMax = 3;
            no = new int[]      {31, 32, 33};
            appear = new int[]  {6,  14, 21};
            cost = new int[]    {1,  5,  10};
            break;
        case Common.ENEMYBASE_TYPE044:
            typeMax = 3;
            no = new int[]      {11, 12, 13};
            appear = new int[]  {0,  8, 18};
            cost = new int[]    {1,  5, 13};
            break;
            
            // 魔の森　敵セット
        case Common.ENEMYBASE_TYPE051:
            typeMax = 6;
            no = new int[]      {1, 2, 21, 41, 52, 43, 53};
            appear = new int[]  {0, 5, 0,  0,  6,  18, 19};
            cost = new int[]    {1, 4, 1,  1,  4,  12, 13};
            break;
        case Common.ENEMYBASE_TYPE052:
            typeMax = 6;
            no = new int[]      {1, 2, 21, 41, 52, 43, 53};
            appear = new int[]  {1, 6, 1,  1,  7,  19, 19};
            cost = new int[]    {1, 4, 1,  1,  4,  12, 13};
            break;
        case Common.ENEMYBASE_TYPE053:
            typeMax = 6;
            no = new int[]      {1, 2, 21, 41, 52, 43, 53};
            appear = new int[]  {2, 7, 2,  2,  8,  20, 19};
            cost = new int[]    {1, 4, 1,  1,  4,  12, 13};
            break;
        case Common.ENEMYBASE_TYPE054:
            typeMax = 6;
            no = new int[]      {1, 2, 21, 41, 52, 43, 53};
            appear = new int[]  {3, 8, 3,  3,  9,  21, 19};
            cost = new int[]    {1, 4, 1,  1,  4,  12, 13};
            break;
            
            // 守護者　敵セット
        case Common.ENEMYBASE_TYPE061:
            typeMax = 6;
            no = new int[]      {1, 2, 21, 41, 52, 43, 53};
            appear = new int[]  {0, 5, 0,  0,  6,  18, 19};
            cost = new int[]    {1, 4, 1,  1,  4,  12, 13};
            break;
            
            // 死者の都　敵セット
        case Common.ENEMYBASE_TYPE071:
            typeMax = 3;
            no = new int[]      {61, 62, 51};
            appear = new int[]  {0,  8,  0};
            cost = new int[]    {1,  5,  1};
            break;
        case Common.ENEMYBASE_TYPE072:
            typeMax = 3;
            no = new int[]      {61, 62, 63};
            appear = new int[]  {3,  10, 17};
            cost = new int[]    {1,  5,  12};
            break;
        case Common.ENEMYBASE_TYPE073:
            typeMax = 3;
            no = new int[]      {61, 62, 63};
            appear = new int[]  {4,  12, 20};
            cost = new int[]    {1,  5,  12};
            break;
            
            // 亡国の王　敵セット
        case Common.ENEMYBASE_TYPE081:
            typeMax = 4;
            no = new int[]      {61, 62, 63, 71};
            appear = new int[]  {0,  8,  15, 2};
            cost = new int[]    {1,  5,  12, 1};
            break;
        case Common.ENEMYBASE_TYPE082:
            typeMax = 4;
            no = new int[]      {61, 62, 63, 71};
            appear = new int[]  {3,  10, 17, 4};
            cost = new int[]    {1,  5,  12, 1};
            break;
            
            // 魔境　敵セット
        case Common.ENEMYBASE_TYPE091:
            typeMax = 3;
            no = new int[]      {71, 72, 73};
            appear = new int[]  {0,  8,  15};
            cost = new int[]    {1,  5,  12};
            break;
            
        case Common.ENEMYBASE_TYPE092:
            typeMax = 3;
            no = new int[]      {21, 22, 3};
            appear = new int[]  {0,  7,  12};
            cost = new int[]    {1,  4,  10};
            break;
            
        case Common.ENEMYBASE_TYPE101:
            typeMax = 5;
            no = new int[]      {22, 3,  71, 72, 73};
            appear = new int[]  {7,  12, 0,  8,  15};
            cost = new int[]    {4,  10, 1,  5,  12};
            break;
            
        case Common.ENEMYBASE_TYPE102:
            typeMax = 3;
            no = new int[]      {31, 32, 33};
            appear = new int[]  {0,  5,  11};
            cost = new int[]    {1,  4,  9};
            break;
        }
        
    }

    public int[] getNo() {
        return no;
    }

    public void setNo(int[] no) {
        this.no = no;
    }

    public int[] getAppear() {
        return appear;
    }

    public void setAppear(int[] appear) {
        this.appear = appear;
    }

    public int[] getCost() {
        return cost;
    }

    public void setCost(int[] cost) {
        this.cost = cost;
    }

    public int getTypeMax() {
        return typeMax;
    }

    public void setTypeMax(int typeMax) {
        this.typeMax = typeMax;
    }
}
