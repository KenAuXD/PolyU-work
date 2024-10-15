#3(a)
setwd("C:/Users/HP/Desktop/23085232d/Q3")
data = read.csv("house_prices_dataset.csv")

par(mfrow = c(2, 2))

area = lm(House_Price ~ House_Area, data)
distance = lm(House_Price ~ Distance_to_Center, data)
age = lm(House_Price ~ House_Age, data )
condition = lm(House_Price ~ House_Condition, data)

#House Area
plot(data$House_Area, data$House_Price, main = "House Price and House Area", 
     xlab = "House Area", ylab = "House Price")
abline(area, col = "red")
coeff_area = coefficients(area)
text(150, 400000, paste0("House Price = ", round(coeff_area[2], 1)," * House Area + ", round(coeff_area[1], 1)), cex = 1.1)


#Distance to Center
plot(data$Distance_to_Center, data$House_Price, main = "House Price and Distance to Center",
     xlab = "Distance to Center", ylab = "House Price")
abline(distance, col = "red")
coeff_distance = coefficients(distance)
text(11,  400000, paste0("House Price = ", round(coeff_distance[2], 1), " * Distance to Center + ", round(coeff_distance[1], 1)), cex = 1.1)


#House Age
plot(data$House_Age, data$House_Price, main = "House Price and House Age",
     xlab = "House Age", ylab = "House Price")
abline(age, col = "red")
coeff_age = coefficients(age)
text(26, 400000, paste0("House Price = ", round(coeff_age[2], 1), " * House Age + ", round(coeff_age[1], 1)), cex = 1.1)


#House Codition
plot(data$House_Condition, data$House_Price, main = "House Price and House Condition",
     xlab = "House Condition", ylab = "House Price")
abline(condition, col = "red")
coeff_condition = coefficients(condition)
text(6, 400000, paste0("House Price = ", round(coeff_condition[2], 1)," * House Condition + ", round(coeff_condition[1], 1)), cex = 1.1)


#3(b)

multimodel = lm(House_Price ~ House_Area + Distance_to_Center, data)
print(paste('$', round(predict(multimodel, data.frame(House_Area = 250, Distance_to_Center = 5)), 4)))

#3(c)

multimodel2 = lm(House_Price ~ House_Area + House_Age,  data)
multimodel3 = lm(House_Price ~ House_Area + House_Condition,  data)
multimodel4 = lm(House_Price ~ House_Age + Distance_to_Center, data)
multimodel5 = lm(House_Price ~ House_Condition + Distance_to_Center, data)
multimodel6 = lm(House_Price ~ House_Age + House_Condition, data)


answer = paste0("The R-squared values for all the models are ",
                   summary(multimodel)$r.squared, ", ",
                   summary(multimodel2)$r.squared, ", ",
                   summary(multimodel3)$r.squared, ", ",
                   summary(multimodel4)$r.squared, ", ",
                   summary(multimodel5)$r.squared, ", ",
                   summary(multimodel6)$r.squared, ". So the best model is Model 2")

print(answer)