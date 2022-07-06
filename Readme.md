<h4>Start tests</h4>
<p>To start test from command line use:<br>
<i>mvn clean test -P dev</i>
</p>
<h4>Parameters:</h4>
<p>By default Chrome is used in our solution. However, Firefox and Opera could be used as well as Chrome.
To chose default browser use property <strong> -Dbrowser</strong> with parameters 
from a list <strong>[chrome, firefox, opera]</strong></p>
<h6>Example:</h6>
<p><i>mvn clean test -P dev -Dbrowser=opera</i></p>
