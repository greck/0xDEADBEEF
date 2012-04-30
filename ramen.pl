#!/usr/bin/perl

use Data::Dumper;

 $stateCount = 0;
$recipeCount = 0;

open FSM, "FSM.dot";

while ( <FSM> ) {

	if ( /START            ->/ ) {
	
		( $start_state ) = /START            -> (\w+);/;
		last;

	}

	( $state, $recipe ) = /(\w+)\s+.+r=(\w+)/ if /r=(\w+)/;

	if ( $state ne "" and $recipe ne "" ) {

		if ( !defined($states{$state}) ) {
		
#			print STDERR "$state is state #" . $stateCount . "\n";
			$states{$state} = $stateCount++;
			
		}
	
		if ( !defined($recipes{$recipe}) ) {
	
#			print STDERR "$recipe is recipe #" . $recipeCount . "\n";
			$recipes{$recipe} = $recipeCount++;
			
		}

		$state2recipe[$states{$state}] = $recipes{$recipe};
#		print STDERR "state #" . $states{$state} . " uses recipe #" . $recipes{$recipe} . "\n";

		undef $state, $recipe;

	}

}

while ( <FSM> ) {

	( $src_state, $dst_state, $condition ) = /(\w+)\s+-> (\w+)\s+\[ label = \"(.+)\" \]/;

	if ( $condition ) {

		$state_machine[$states{$src_state}][0] = $state2recipe[$states{$src_state}];
		$state_machine[$states{$src_state}][1] = $states{$dst_state} if $condition =~ /T/;		
		$state_machine[$states{$src_state}][2] = $states{$dst_state} if $condition =~ /F/;		

		undef $condition;

	}

}

print <<END;
package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.nxt.comm.RConsole;

public class FSM {

	public static void bobbyHenderson() {
	
END

print "\t\tRecipe[] r = new Recipe[$recipeCount];\n";

for ( my $i = 0; $i < $recipeCount; $i++ ) {

	foreach $r ( keys %recipes ) {

		print "\t\tr[$recipes{$r}] = new $r();\n" if $recipes{$r} == $i;

	}

}

print "\n\t\tint[][] s = new int[$stateCount][3];\n\n";
print "//\t\t     +----------+\n";
print "//\t\t     |          |\n";
print "//\t\t     v          v\n";

for ( my $i = 0; $i < $stateCount; $i++ ) {

	print "\t\ts[$i][0] = $state_machine[$i][0];";
	print " // recipe to execute" if $i == 0;
	print "\n";
	print "\t\ts[$i][1] = $state_machine[$i][1];";
	print " // next state if recipe return true" if $i == 0;
	print "\n";
	print "\t\ts[$i][2] = $state_machine[$i][2];";
	print " // next state if recipe returns false" if $i == 0;
	print "\n";

	if ( $i == 0 ) {

		print "//\t\t  ^       ^\n";
		print "//\t\t  |       |\n";
		print "//\t\t  |       this is the state to transition to\n";
		print "//\t\t  |		\n";
		print "//\t\t  this is the current state\n";
	
	}

	print "\n" if $i < $stateCount - 1;

}

print <<END;
		
		// free and clear to navigate!
		//
		Sound.twoBeeps();

		// INITIAL STATE
		//
END

print "\t\tint state = " . $states{$start_state} . ";\n";		

print <<END;

		while ( RobotState.poll() ) {

			switch(state) {

END

for ( my $i = 0; $i < $stateCount; $i++ ) {

	foreach $s ( keys %states ) {

		$stateStr = $s if $states{$s} == $i;

	}

    $stateStr .= '_' x ( 16 - length($stateStr) );  

	print "\t\t\t\tcase ";
	print $i>9?"":" ";
	print "$i:\n";
	print "\t\t\t\t\tLCD.drawString(\"$stateStr\",0,0);\n";
	print "\t\t\t\t\tif ( Robot.DEBUG ) { RConsole.println(\"$stateStr\"); }\n";
	print "\t\t\t\t\tbreak;\n";

}

print <<END;
				
			}
			
			if( r[s[state][0]].execute() ) {
				state = s[state][1];
			} else {
				state = s[state][2];
			}

		}
	}
}
END
