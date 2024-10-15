set.seed(1234)

#2(a)
normal = rnorm(10000, 0.02, 0.05)
log = exp(normal)

hist(log, breaks=30, probability = TRUE ,col = "gray", xlab = "Value", main = "log-normal distribution")


#2(b)
x = seq(min(log), max(log), length.out = 100)
density = dlnorm(x, 0.02, 0.05)

lines(x, density, type = "l", col = "red", lwd = 2)

#2(c) for testing the empirical mean and variance with different sample size :D
print(mean(log))
print(var(log))

#2(d)
prob = mean(log >= 1.05)
print(prob)

#2(e)
proB = plnorm(1.05, 0.02, 0.05)
print(1 - proB)