clear all;

inputs = xlsread('CarEvaluation.xlsx', 'ANN inputs')';
desiredOutputs = xlsread('CarEvaluation.xlsx', 'ANN outputs')';

arch = [2];
%arch = [5];
%arch = [4 2];
%arch = [8 4]

ann = patternnet(arch);
[ann tr] = train(ann, inputs, desiredOutputs);

