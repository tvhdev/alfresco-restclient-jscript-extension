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
# compile to create AMP file
mvn clean install -DskipTests

# install target/alfresco-restclient-jscript-extension-1.0-SNAPSHOT.amp on alfresco repo server
java -jar alfresco-mmt*.jar ...


Enjoy, and please fix bugs as there could be some;)
