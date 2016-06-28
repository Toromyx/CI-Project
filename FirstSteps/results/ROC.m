clear all, clc, close all
data = csvread('Blosum30DataForROC.csv');

x = data(:,1);
y = data (:,2);

figure;
hold on
plot(x,y)
area = trapz(x,y)