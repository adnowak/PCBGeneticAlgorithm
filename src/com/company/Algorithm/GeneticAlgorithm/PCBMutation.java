package com.company.Algorithm.GeneticAlgorithm;

import com.company.Models.PCB;
import com.company.Models.PCBConnection;
import com.company.Models.PCBConnectionSegment;
import com.company.Utils;

public class PCBMutation {
    private PCB pcb;
    private int pcbWidth;
    private int pcbHeight;

    public PCBMutation(PCB pcb) {
        this.pcb = new PCB(pcb);
        pcbWidth = pcb.getWidth();
        pcbHeight = pcb.getHeight();
    }

    public PCB mutate(){
        for (PCBConnection chromosome : pcb.getConnections()) {
            if(Math.random() < Utils.MUTATION_PROB){
                int segmentIndex = Utils.getRandomNumber(0, chromosome.getPath().size());
                int lengthChange = Utils.getRandomNumber(0,2) == 0 ? -1 : 1;

                if(chromosome.getPath().size() > 2){
                    if(segmentIndex>0 && segmentIndex<chromosome.getPath().size()-2) {//change length of a segment
                        if(chromosome.getPath().get(segmentIndex).isHorizontal()){
                            int positionIndex = chromosome.getPath().get(segmentIndex).getDirectionalLength() + lengthChange + chromosome.getPath().get(segmentIndex).getStartingPosX();
//                            System.out.println("Position index: " + positionIndex);
                            if(positionIndex > 0 && positionIndex < pcbWidth){
                                chromosome.getPath().get(segmentIndex+1).changeStartingPosX(lengthChange);
                                chromosome.getPath().get(segmentIndex+2).changeStartingPosX(lengthChange);

                                chromosome.getPath().get(segmentIndex).changeDirectionalLength(lengthChange);
                                chromosome.getPath().get(segmentIndex+2).changeDirectionalLength(-lengthChange);
                            }
                        }
                        else {
                            int positionIndex = chromosome.getPath().get(segmentIndex - 1).getDirectionalLength() + lengthChange + chromosome.getPath().get(segmentIndex - 1).getStartingPosY();
//                            System.out.println("Position index: " + positionIndex);
                            if(positionIndex > 0 && positionIndex < pcbHeight) {
                                chromosome.getPath().get(segmentIndex+1).changeStartingPosY(lengthChange);
                                chromosome.getPath().get(segmentIndex+2).changeStartingPosY(lengthChange);

                                chromosome.getPath().get(segmentIndex).changeDirectionalLength(lengthChange);
                                chromosome.getPath().get(segmentIndex+2).changeDirectionalLength(-lengthChange);
                            }
                        }
                    }
                    else if(segmentIndex == chromosome.getPath().size()-2) {//change length of a segment
                        if(chromosome.getPath().get(segmentIndex).isHorizontal()){
                            int positionIndex = chromosome.getPath().get(segmentIndex).getDirectionalLength() + lengthChange + chromosome.getPath().get(segmentIndex).getStartingPosY();
//                            System.out.println("Position index: " + positionIndex);
                            if(positionIndex > 0 && positionIndex < pcbHeight){
                                chromosome.getPath().get(segmentIndex+1).changeStartingPosX(lengthChange);

                                chromosome.getPath().get(segmentIndex).changeDirectionalLength(lengthChange);

                                chromosome.getPath().add(new PCBConnectionSegment(true, -lengthChange, chromosome.getPath().get(segmentIndex+1).getStartingPosX(), positionIndex));
                            }
                        }
                        else {
                            int positionIndex = chromosome.getPath().get(segmentIndex - 1).getDirectionalLength() + lengthChange + chromosome.getPath().get(segmentIndex - 1).getStartingPosX();
//                            System.out.println("Position index: " + positionIndex);
                            if(positionIndex > 0 && positionIndex < pcbWidth) {
                                chromosome.getPath().get(segmentIndex+1).changeStartingPosY(lengthChange);

                                chromosome.getPath().get(segmentIndex).changeDirectionalLength(lengthChange);

                                chromosome.getPath().add(new PCBConnectionSegment(false, -lengthChange, positionIndex, chromosome.getPath().get(segmentIndex+1).getStartingPosY()));
                            }
                        }
                    }
                }
//                System.out.println("-------------------------");
            }
        }
        return pcb;
    }
}
