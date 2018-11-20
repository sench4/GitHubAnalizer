function getBaseURL(){
    var baseUrl = window.location;
    baseUrl = baseUrl.protocol + "//" + baseUrl.host + "/" + baseUrl.pathname.split('/')[1];
    return baseUrl;
}