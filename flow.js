var benchrest = require('bench-rest');
var i = 0;
var j = 0;

function makeWord()
{
    var text = "";
    var possible = "ABCDE";

    for( var i=0; i < 5; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

function randomDate(start, end) {
    return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
}


var flow = {

    main: [
        { beforeHooks :[function(all){
            i++;
            if(i > 10){
                j++;
                i=0;
            }

            all.requestOptions.json ={
                    "SenderType": "FME",
                    "SenderName": makeWord(),
                    "TaskKey":  j,
                    "TaskPriority": 4,
                    "TaskUtcCreationDate": new Date(2016, 3, 1).toJSON() ,
                    "TaskUtcDueDate": new Date().toJSON(),
                    "ActivityKey": "Activity1Key",
                    "ActivityName": "Activity1Desc",
                    "Metadata": [
                        { "Key": "MetaData1",
                            "Value": makeWord()
                        },
                        { "Key": "MetaData2",
                            "Value": makeWord()
                        },
                        { "Key": "MetaData3",
                            "Value": makeWord()
                        },
                        { "Key": "MetaData4",
                            "Value": makeWord()
                        },
                        { "Key": "MetaData5",
                            "Value": makeWord()
                        },
                        { "Key": "MetaData6",
                            "Value": makeWord()
                        },
                        { "Key": "MetaData7",
                            "Value": makeWord()
                        }
                    ],
                    "UtcEventDate":randomDate(new Date(2016, 3, 1), new Date()).toJSON()
            };
            return all;
        }], post: 'http://127.0.0.1:8080/api/rest/messages/queue' }
    ]
};

module.exports = flow;