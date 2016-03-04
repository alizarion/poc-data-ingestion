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
                    "TaskUtcCreationDate": "2014-06-14T18:00:00."+all.env.index ,
                    "TaskUtcDueDate": "2014-06-16T18:00:00.0000000"+all.env.index,
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
                    "UtcEventDate": "2014-06-14T17:19:00."+all.env.index
            };
            return all;
        }], post: 'http://127.0.0.1:8080/api/rest/messages/queue' }
    ]
};

module.exports = flow;