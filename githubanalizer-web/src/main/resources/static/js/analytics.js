function loadCommitters(tab, repositoryName, ownerName){
    return $.getJSON(getBaseURL() + "/analytics/" + tab, {
        repositoryName: repositoryName,
        ownerName: ownerName
    }).done(function(data) {
        var $tabContent = $('#'+tab);
        $tabContent.empty();
        $tabContent.append($('<hr>'));

        data.forEach(function(commiter){
            $tabContent.append($('<div>')
                .append($('<b>').append(document.createTextNode('Name: ')))
                .append(document.createTextNode(commiter.name)).append($('<br>'))
                .append($('<b>').append(document.createTextNode('Email: ')))
                .append(document.createTextNode(commiter.email)));
            $tabContent.append($('<hr>'));
        });
    }).fail(function(xhr){
        alert('Failed to load data. Try again!')
    });
}

function loadCommits(tab, repositoryName, ownerName){
    return $.getJSON(getBaseURL() + "/analytics/" + tab, {
        repositoryName: repositoryName,
        ownerName: ownerName
    }).done(function(data) {
        var $tabContent = $('#'+tab);
        $tabContent.empty();
        $tabContent.append($('<hr>'));

        data.forEach(function(commit){
            $tabContent.append($('<div>')
                .append($('<b>').text(commit.commiter.name))
                .append(document.createTextNode(' commited on '))
                .append($('<b>').text(commit.commiter.commitDate)))
                .append($('<b>').text('Commit message: '))
                .append(document.createTextNode(commit.message));
            $tabContent.append($('<hr>'));
        });
    }).fail(function(xhr){
        alert('Failed to load data. Try again!')
    });
}

function loadImpacts(tab, repositoryName, ownerName) {
    return $.getJSON(getBaseURL() + "/analytics/" + tab, {
        repositoryName: repositoryName,
        ownerName: ownerName
    }).done(function(data) {
        var $tabContent = $('#'+tab);
        $tabContent.empty();
        $tabContent.append($('<hr>'));

        data.forEach(function(impact){
            $tabContent.append($('<div>')
                .append($('<b>').append(document.createTextNode('Name: ')))
                .append(document.createTextNode(impact.commiter.name)).append($('<br>'))
                .append($('<b>').append(document.createTextNode('Total commits: ')))
                .append(document.createTextNode(impact.impact)));
            $tabContent.append($('<hr>'));
        });
    }).fail(function(xhr){
        alert('Failed to load data. Try again!')
    });
}