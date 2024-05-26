class Solution {
    int[][] dirs;
    int[][] dp;
    int m;
    int n;
public int[][] updateMatrix(int[][] mat) {
    if(mat == null || mat.length == 0)
    {
        return mat;
    }

    this.m = mat.length;
    this.n = mat[0].length;
    dirs = new int[][] {{1,0} , {-1,0}, {0, -1}, {0,1}};

    Queue<int[]> q = new LinkedList<>();

    for(int i=0; i<m; i++)
    {
        for(int j=0; j<n; j++)
        {
            if(mat[i][j] == 1)
                 mat[i][j] = -1;
        }
    }
    
    this.dp = new int[m][n];
    for(int i=0; i<m; i++)
    {
        for(int j=0; j<n; j++)
        {
            if(mat[i][j] == -1)
                dp[i][j] = dfs(mat, i, j);
        }
    }
    return dp;  
}
private int dfs(int[][] mat, int i, int j)
{
    //base
    if(i < m-1 && mat[i+1][j] == 0) return 1;
    if(i > 0 && mat[i-1][j] == 0) return 1;
    if(j <n-1 && mat[i][j+1] == 0) return 1;
    if(j>0 && mat[i][j-1] == 0) return 1;


    //logic
    int top = 99999; int bottom = 99999;
    int left = 99999; int right = 99999;

    //top
    if(i> 0 && dp[i-1][j] != 0)
    {
        top = dp[i-1][j];
    }

    //left
    if(j > 0 && dp[i][j-1] != 0)
    {
        left = dp[i][j-1];
    }

    //bottom
    if(i < m-1)
    {
        if(dp[i+1][j] == 0)
        {
            dp[i+1][j] = dfs(mat, i+1, j);
        }
        bottom = dp[i+1][j];
    }

    //right
    if(j < n-1)
    {
        if(dp[i][j+1] == 0)
        {
            dp[i][j+1] = dfs(mat, i, j+1);
        }
        right = dp[i][j+1];
    }
    return Math.min(right, Math.min(top, Math.min(bottom,left)))+1;
}
}