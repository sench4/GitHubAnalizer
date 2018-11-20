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

    doneTyping() {
        if($('#searchRepository').val().trim().length > 1) {
            this.setState({open: true, loading: false, suggestedRepositories: []});

            return $.getJSON(getBaseURL() + "/search/repositories", {
                repo: $('#searchRepository').val().trim(),
            }).then((data) => {
                if (this.state.open) {
                    this.setState({
                        suggestedRepositories: data.items ? data.items : [],
                        totalRepoCount: data.total_count
                    });
                }
            })
            .fail(function(xhr){
                alert('Search failed. Try again!')
            });
        }
        else{
            this.setState({open: false, loading: false});
        }
    }

    render(){
        const suggestedRepositories = this.state.suggestedRepositories.map((suggestedRepository, i) => {
            return (<li>
                <a>
                    <span>{suggestedRepository.name}</span>
                </a>
            </li>);
        });

        const loading = (this.state.loading ? <Loading display={"inline-block"} className="marginTop10"/> : '');

        return (<div id="repositorySearchControl" className={this.state.open && this.state.suggestedRepositories.length > 0 ? "dropdown open" : "dropdown"}>
                    <span className="searchDescription">Type to search repository</span>
                    <input className="form-control input-lg searchRepository" id="searchRepository" placeholder="Search Repository..." type="text"
                           onKeyUp={this.handleKeyUp} onKeyDown={this.handleKeyDown}/>{loading}
                    <ul className="dropdown-menu">
                        <li><button type="button" className="close closeDropDown" onClick={() => {this.setState({open: false});}}><b>&times;</b></button></li>
                        {suggestedRepositories}
                    </ul>
        </div>);
    }
}