Certain steps would have to be proceeded by certain characters.  

"An instruction should be able to cover everything 
besides the special grammar characters that make a new machines"

on second thought of that a new machine should be able to be used as an instruction it's just that one
global machine will have to be the entry point that has the initial state of defaults. that way then it should 
definitely be able to represent any of those demonstrations from that book.

You can think of a machine as actually just doing one instruction and not actually have to take up the memory space
of generating every thing and doing their state.  You simply just push and pop the state on the stack, peek it to use it.  When a new machine is created the rest of the valid instructions simply get added to the new machine.  A render frame would literally just be every machine in the list executing their current instuction b one step, updating their time, if time passed set up the next step.

Any drawing instructions should probably be implemented by some lerp you could make full things by giving instuctions with duration of 0 would mean they happen instantaneously

overall states:
generative vs iterative drawing
anim vs final state (this i don't know about because all the instruction's are defined as paths) maybe animation speed can be increased but thats really just frame rate if it's an actor drawing)
2d vs 2d (changes the process) Probably only want to do in 3d in order to move camera and what not
####some possible types:

###Machine States
splitMachines (new machines that will execute instructions untill popped)
popMachines
###State Changes
pushstate
popstate (if a machine is alive and still has instructions it ignores this command, this is how you would give a new state to a new machine while not executing any further instruction until the machine that created the new one is back to it's state to execute subsequent instructions)
rotateX:Y:Z
rotation plane
visible (if flip a machine to invisible, then push new machines, this one can act as an orchestrator to remain until the new machines finished their instuctions )


line: 
curve: radius, start degree, end degree  (3d may be difficult, there needs to be a state for curve draws for which plane specified)
origin
pushstate
popstate
pushexistingMachine
popexistingmachine
rotate
orbit a point  (3d may be difficult)
place an object (can have )
flip states draw vs move
move invisble vs no

for things that we want continuosly drawn with trails can be it own instruction as well

also since we can get the pixel on the screen we can do some after glow affects as well

http://algorithmicbotany.org/papers/#abop

you have one machine to start with the original state
any machines created 
The big thing to remember is that any new instructions only get applied to top of the machine stack.  You can't pop the original machine

I think all instructions in a generation do have to be calculated in order to properly create new machines

I think I am trying to only animate one per generation.  So each generation shouldn't be dependent on the last generation, it should start with a fresh origin, there may be something to explore vs it's not, it basically means a more complicated iteration is drawn starting at the end points of the last machines, if you do iterativlely larger then you should have a version where every machine execute the instructions or only this generations machines