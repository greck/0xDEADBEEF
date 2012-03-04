#!/usr/bin/perl

open FSM, "FSM.dot";

while ( <FSM> ) {

	( $recipe ) = /r=(\w+)/ if /r=(\w+)/;

	( $src_state, $dst_state, $condition ) = /(\w+) -> (\w+) \[ label = \"(.+)\" \]/;

#	print "[$src_state][$dst_state][$condition][$recipe];\n" if $condition;

	if ( $condition ) {

		if ( !defined($recipes{$recipe}) ) {
		
			$recipes{$recipe} = $recipeCount++;
			
		}

		if ( !defined($states{$src_state}) ) {
		
			$states{$src_state} = $stateCount++;
			
		}

		if ( !defined($states{$dst_state}) ) {
		
			$states{$dst_state} = $stateCount++;
			
		}
		

		$state_machine[$states{$src_state}][0] = $recipes{$recipe};
		$state_machine[$states{$src_state}][1] = $states{$dst_state} if $condition =~ /T/;		
		$state_machine[$states{$src_state}][2] = $states{$dst_state} if $condition =~ /F/;		

	}

}

print "\tRecipe[] r = new Recipe[$recipeCount];\n";

for ( my $i = 0; $i < $recipeCount; $i++ ) {

	foreach $r ( keys %recipes ) {

		print "\tr[$recipes{$r}] = new $r();\n" if $recipes{$r} == $i;

	}

}

print "\n\tint[][] s = new int[$stateCount][3];\n\n";
print "//\t     +----------+\n";
print "//\t     |          |\n";
print "//\t     v          v\n";

for ( my $i = 0; $i < $stateCount; $i++ ) {

	print "\ts[$i][0] = $state_machine[$i][0];";
	print " // recipe to execute" if $i == 0;
	print "\n";
	print "\ts[$i][1] = $state_machine[$i][1];";
	print " // next state if recipe return true" if $i == 0;
	print "\n";
	print "\ts[$i][2] = $state_machine[$i][2];";
	print " // next state if recipe returns false" if $i == 0;
	print "\n";

	if ( $i == 0 ) {

		print "//\t  ^       ^\n";
		print "//\t  |       |\n";
		print "//\t  |       this is the state to transition to\n";
		print "//\t  |		\n";
		print "//\t  this is the current state\n";
	
	}

	print "\n" if $i < $stateCount - 1;

}
