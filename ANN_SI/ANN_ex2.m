clear all;

inputs = xlsread('CarEvaluation.xlsx', 'ANN inputs')';
desiredOutputs = xlsread('CarEvaluation.xlsx', 'ANN outputs')';

arch = {[], [3], [5], [8 4]};

for i = 1:length(arch)
    architecture = arch{i};

    precisionTrain = []; 
    precisionValid = []; 
    precisionTest = []; 

    for j=1:50 
        ann = patternnet(architecture);
        ann.trainParam.showWindow = false; 
        [ann tr] = train(ann, inputs, desiredOutputs); 
        carOuputs = sim(ann, inputs); 

        precisionTrain(end+1) = 1-confusion(desiredOutputs(:,tr.trainInd), carOuputs(:,tr.trainInd)); 
        precisionValid(end+1) = 1-confusion(desiredOutputs(:,tr.valInd),carOuputs(:,tr.valInd)); 
        precisionTest(end+1) = 1-confusion(desiredOutputs(:,tr.testInd), carOuputs(:,tr.testInd)); 
    end;
    fprintf('Train %.2f%% (%.2f), val:%.2f%% (%.2f), test:%.2f%% (%.2f)\n', mean(precisionTrain), std(precisionTrain), mean(precisionValid), std(precisionValid), mean(precisionTest), std(precisionTest))
end;