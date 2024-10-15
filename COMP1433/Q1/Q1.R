library(ggplot2)
setwd("C:/Users/HP/Desktop/23085232d/Q1")
data = read.csv("Customers.csv")
par(mfrow = c(2, 1))

x = samples$Annual_Income
y = samples$Spending_Score

centroids_x = c(5, 20, 100, 70, 120)
centroids_y = c(5, 90, 10, 50, 100)

i = 1
for(i in 1:100){
  clus = c()
  for(j in 1:200){
    distance = c()
    for(k in 1:5){
      d = sqrt((centroids_x[k] - x[j])^2 + (centroids_y[k] - y[j])^2)
      distance = append(distance, d)
    }
    clus <- append(clus, min(distance))
  }
  Data <- data.frame(annual_income <- x, spending_score <- y, clusterindex <- clus)
  

  centroids_x <- aggregate(Data$annual_income, list(Data$clusterindex), FUN = mean)
  centroids_y <- aggregate(Data$spending_score, list(Data$clusterindex), FUN = mean)
  
}


color <- c("1" = "red", "2" = "green",
           "3" = "blue", "4" = "purple", "5" = "brown")

p1 <- ggplot(data, aes(x = annual_income, y = spending_score, color = factor(clusterindex))) +
  geom_point() +
  scale_color_manual(values = color) +
  labs(title = "K-means Clustering Plot", x = "Annual Income", y = "Spending Score",
       color = "Clusters")
plot(p1)