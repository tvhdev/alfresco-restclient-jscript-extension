# alfresco-restclient-jscript-extension, tested with Alfresco Community Edition 7.0.0
This extension adds a simple REST-Client to the alfresco repo, which can be used within goldhammer's Javascript Admin Console component (see https://github.com/share-extras/js-console). Using the simple web service client it is possible to request data from different sources to fill a document's meta-attributes automatically within a executed rule-set.

The REST-Client works like this:
```javascript
// with "anonymous" access
var ret = restclient.get("https://www.heise.de");
print(ret);

// with "basic" http-authentication
var ret = restclient.get("https://www.bank.de","kontonummer","passwort");
print(ret);

```
# Compilation
mvn clean install -DskipTests

# Installation
The jscript-extension has been developed for an existing Alfresco 7.0 installation. The alfresco-restclient-jscript-extension-<version>.amp needs to be installed into the Alfresco Repository webapp using the Alfresco Module Management Tool (MMT):

java -jar alfresco-mmt.jar install alfresco-restclient-jscript-extension-<version>.amp /path/to/alfresco.war
  
Enjoy, and please fix bugs as there could be some;)
