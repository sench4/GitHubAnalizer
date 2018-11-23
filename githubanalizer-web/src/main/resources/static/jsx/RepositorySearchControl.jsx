class RepositorySearchControl extends React.Component{
    constructor(props){
        super(props);

        this.typingTimer;                //timer identifier
        this.doneTypingInterval = 500;  //time in ms, 0.5 second

        this.state = {
            open: false,
            loading: false,
            suggestedRepositories: [],
            totalRepoCount: 0
        }

        this.handleKeyUp = this.handleKeyUp.bind(this);
        this.handleKeyDown = this.handleKeyDown.bind(this);
        this.handleShowMoreClick = this.handleShowMoreClick.bind(this);
        this.handleRepositoryClick = this.handleRepositoryClick.bind(this);
        this.doneTyping = this.doneTyping.bind(this);
    }

    componentDidMount(){
        $('#searchRepository').focus();
    }

    handleKeyDown(){
        this.setState({open: false, loading: true});
        clearTimeout(this.typingTimer);
    }

    handleKeyUp(){
        clearTimeout(this.typingTimer);
        this.typingTimer = setTimeout(this.doneTyping, this.doneTypingInterval);
    }

    handleShowMoreClick(){
        window.location.href = getBaseURL() + '/repositories/search-result?repo=' + $('#searchRepository').val().trim() + '&pageNumber=1&pageSize=100';
    }

    handleRepositoryClick(repositoryName, ownerName){
        window.location.href = getBaseURL() + '/analytics?repositoryName=' + repositoryName + '&ownerName=' + ownerName ;
    }

    doneTyping() {
        var _this = this;
        if($('#searchRepository').val().trim().length > 1) {
            _this.setState({open: true, suggestedRepositories: [], totalRepoCount: 0});

            return $.getJSON(getBaseURL() + "/repositories/search", {
                repo: $('#searchRepository').val().trim(),
                pageNumber: 0,
                pageSize: 10
            }).then((data) => {
                if (_this.state.open) {
                    _this.setState({
                        suggestedRepositories: data ? data.items : [],
                        totalRepoCount: data.total_count,
                        loading: false,
                        open: true
                    });
                }

                if(data.total_count == 0){
                    $('#searchRepository').notify("No repository found", { position:"bottom center", className: "warning"});
                }
            })
            .fail(function(xhr){
                _this.setState({open: false, loading: false});
                alert('Search failed. Try again!')
            });
        }
        else{
            this.setState({open: false, loading: false});
        }
    }

    render(){
        const suggestedRepositories = this.state.suggestedRepositories.map((suggestedRepository, i) => {
            return (<li onClick={() => {this.handleRepositoryClick(suggestedRepository.name, suggestedRepository.owner.login);}}>
                <a>
                    <span>{suggestedRepository.name}</span>
                </a>
            </li>);
        });

        const loading = (this.state.loading ? <Loading display={"inline-block"} className="marginTop10"/> : '');

        return (<div id="repositorySearchControl" className={this.state.open && this.state.suggestedRepositories.length > 0 ? "dropdown open repositorySearchControl" : "dropdown repositorySearchControl"}>
                    <div className="searchDescription">Type to search repository</div>
                    <div>
                        <input className="form-control input-lg searchRepository" id="searchRepository" placeholder="Search Repository..." type="text"
                               onKeyUp={this.handleKeyUp} onKeyDown={this.handleKeyDown}/>{loading}
                    </div>
                    <ul className="dropdown-menu">
                        <li><button type="button" className="close closeDropDown" onClick={() => {this.setState({open: false});}}><b>&times;</b></button></li>
                        {suggestedRepositories}
                        {this.state.suggestedRepositories.length < this.state.totalRepoCount ?
                            <li className="text-center" onClick={this.handleShowMoreClick}><a><span className="showMore">{this.state.totalRepoCount} results found. Show more...</span></a></li> : ''
                        }
                    </ul>
        </div>);
    }
}