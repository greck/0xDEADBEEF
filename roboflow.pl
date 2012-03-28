#!/usr/bin/perl

use Data::Dumper;

 $stateCount = 0;
$recipeCount = 0;

open FSM, "FSM.dot";

while ( <FSM> ) {

	last if /->/;

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

print "\n###########################\n\n";

for ( my $i = 0; $i < $stateCount; $i++ ) {

	foreach $s ( keys %states ) {

		$stateStr = $s if $states{$s} == $i;

	}

    $stateStr .= '_' x ( 16 - length($stateStr) );  

	print "\t\tcase ";
	print $i>9?"":" ";
	print "$i: LCD.drawString(\"$stateStr\",0,0); break;\n";

}
