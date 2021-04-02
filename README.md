# alfresco-restclient-jscript-extension
This extension adds a simple restclient to Goldhamer's Javascript Admin Console component (see https://github.com/share-extras/js-console). 

The REST-Client works like this:
```javascript
// with "anonymous" access
var ret = restclient.get("https://www.heise.de");
print(ret);

// with "basic" http-authentication
var ret = restclient.get("https://www.bank.de","kontonummer","passwort");
print(ret);

```

Enjoy, and please fix bugs as there could be some;)
