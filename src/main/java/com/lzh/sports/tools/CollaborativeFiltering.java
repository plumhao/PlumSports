package com.lzh.sports.tools;

import java.util.HashMap;

/**
 * 皮尔逊相关系数协同过滤算法
 *
 * 什么是协同过滤算法
 * 协同过滤算法是一种基于用户行为数据的推荐算法，其基本思想是通过分析用户行为数据，找到不同用户之间的相似性，从而预测用户对未知物品的评分或偏好，从而给用户提供个性化推荐。
 *
 * 协同过滤算法分为两种：基于用户的协同过滤和基于物品的协同过滤。
 * 基于用户的协同过滤
 * 基于用户的协同过滤算法是通过分析用户的历史行为数据，找到与目标用户行为相似的其他用户，从而推荐目标用户可能感兴趣的物品。
 *
 * 具体步骤如下：
 * 计算用户之间的相似度，如皮尔逊相关系数、余弦相似度等。
 * 找到与目标用户相似度最高的K个用户。
 * 综合K个用户对某个物品的评分，预测目标用户对该物品的评分或偏好。
 * 推荐目标用户评分最高的N个物品。
 * 协同过滤算法的优点是可以处理任何类型的物品和用户行为，同时还可以提供高度个性化的推荐。但是，它也存在一些问题，如数据稀疏性、冷启动问题、可扩展性等。因此，在实际应用中需要根据具体情况进行优化和改进。
 *
 * 皮尔森(pearson)相关系数公式
 * 皮尔森相关系数是用来衡量变量之间的线性相关性。但是有一个明显的缺陷就是，它只对线性关系敏感。
 * 如果关系是非线性的，哪怕两个变量之间是一一对应的关系，皮尔森相关系数也可能接近0.
 */
public class CollaborativeFiltering {
    // 第一个参数为用户id, HashMap<Integer, Integer> 为用户的关联的物品   比如商品id+评分 或则商品id+浏览时间 或则文章id+评分
    public  HashMap<Integer, HashMap<Integer, Double>> userRatings;

    /**
     * 初始化构造
     */
    public CollaborativeFiltering()
    {
        userRatings = new HashMap<Integer, HashMap<Integer, Double>>();
    }

    // 添加用户的数据
    public void AddUserRating(Integer userId, Integer itemId, Double rating)
    {
        if (!userRatings.containsKey(userId))
        {
            HashMap<Integer, Double> integerIntegerHashMap = userRatings.get(userId);
            userRatings.put(userId,new HashMap<Integer, Double>());
        }

        HashMap<Integer, Double> integerDoubleHashMap = userRatings.get(userId);
        integerDoubleHashMap.put(itemId,rating);

    }

    // 计算两个向量的点积
    private double DotProduct(HashMap<Integer, Double> vectorA, HashMap<Integer, Double> vectorB)
    {
        double dotProduct = 0;
        for (Integer i : vectorA.keySet()) {
            if (vectorB.containsKey(i)) {
                dotProduct+= vectorA.get(i) * vectorB.get(i);
            }
        }

        return dotProduct;
    }

    // 计算向量的模长
    private double Magnitude(HashMap<Integer, Double> vector)
    {
        double magnitude = 0;
        for (Integer i : vector.keySet()) {
            magnitude += vector.get(i)*vector.get(i) ;
        }

        return Math.sqrt(magnitude);
    }

    // 计算余弦相似度
    public double CalculateCosineSimilarity(Integer userId1, Integer userId2)
    {
        if (!userRatings.containsKey(userId1) || !userRatings.containsKey(userId2))
        {
            return 0;
        }

        HashMap<Integer, Double> ratings1 = userRatings.get(userId1);
        HashMap<Integer, Double> ratings2 = userRatings.get(userId2);

        double dotProduct = DotProduct(ratings1, ratings2);
        double magnitude1 = Magnitude(ratings1);
        double magnitude2 = Magnitude(ratings2);

        if (magnitude1 == 0 || magnitude2 == 0)
        {
            return 0;
        }

        return dotProduct / (magnitude1 * magnitude2);
    }

    /**
     * 加权平均值
     * 加权平均的权重如何设定
     * 设定加权平均的权重需要结合具体的数据特征和研究目标。
     * 在实际应用中，常见的计算权重方法有CRITIC权重法、信息量权重法、AHP层次法和优序图法等。
     * 这些方法利用了数据的信息浓缩原理，数字的相对大小信息进行权重计算。
     * 例如，如果数据波动性大，可以考虑使用CRITIC权重法或信息量权重法；如果是专家打分数据，那么可以使用AHP层次法或优序图法。
     * 此外，有些情况下会将主成分和AHP层次分析法结合来计算综合权重。选择适合的权重计算方法，有助于提高研究的科学性和准确性。
     * 加权平均是一种统计方法，用于计算一组数值的平均值，其中每个数值都有一个相应的权重。加权平均的计算公式为：
     * 加权平均 = (数值1 × 权重1 + 数值2 × 权重2 + ... + 数值n × 权重n) / (权重1 + 权重2 + ... + 权重n)
     * 例如，如果你有一组成绩，每个成绩有一个对应的权重（如作业、考试等），你可以使用加权平均来计算这组成绩的平均分。
     */

    public double WeightedResults(Double[][] values)
    {
        //value[0] 数值
        //value[1] 权重
        Double numerator=Double.valueOf(0);
        Double  denominator=Double.valueOf(0);
        for (Double[] value : values) {
            numerator+=value[0]*value[1];
            denominator+=value[1];
        }
        if(denominator==0)
        {
            denominator=Double.valueOf(1);
        }
        return numerator/denominator;
    }
}
